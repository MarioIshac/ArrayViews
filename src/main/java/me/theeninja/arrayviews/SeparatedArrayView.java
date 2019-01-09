package me.theeninja.arrayviews;

import java.util.ArrayList;

public class SeparatedArrayView<E> extends ArrayView<E> {
	private final int[] arrayIndices;

	public static <E> SeparatedArrayView<E> of(final E[] array, final int... arrayIndices) {
		for (int arrayIndex : arrayIndices) {
			if (!(0 <= arrayIndex && arrayIndex < array.length)) {
				throw new IllegalArgumentException("Provided array index is not valid.");
			}
		}

		return new SeparatedArrayView<>(array, arrayIndices);
	}

	public static <E> SeparatedArrayView<E> of(final E[] array, final int skipStart, final int skipLength) {
		final int[] arrayIndices = newArrayIndices(array, skipStart, skipLength);

		return SeparatedArrayView.of(array, arrayIndices);
	}

	public static <E> SeparatedArrayView<E> of(final E[] array, final int skipLength) {
		return SeparatedArrayView.of(array, 0, skipLength);
	}

	SeparatedArrayView(final E[] array, final int... arrayIndices) {
		super(array);

		this.arrayIndices = arrayIndices;
	}

	private static <E> int[] newArrayIndices(final E[] array, final int skipStart, final int skipLength) {
		final int indicesCount = (array.length - skipStart) / skipLength;

		int[] arrayIndices = new int[indicesCount];
		int arrayIndexIndex = 0;

		for (int arrayIndex = skipStart; arrayIndex < array.length; arrayIndex += skipLength) {
			arrayIndices[arrayIndexIndex++] = arrayIndex;
		}

		return arrayIndices;
	}

	@Override
	public int size() {
		return getArrayIndices().length;
	}

	@Override
	E getHelper(int indexInArrayIndices) {
		final int indexInArray = getArrayIndices()[indexInArrayIndices];

		return getArray()[indexInArray];
	}

	@Override
	public GetterArrayViewIterator<E, SeparatedArrayView<E>> iterator() {
		return new GetterArrayViewIterator<>(this);
	}

	public int[] getArrayIndices() {
		return arrayIndices;
	}
}
