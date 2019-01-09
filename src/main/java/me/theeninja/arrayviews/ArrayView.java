package me.theeninja.arrayviews;

import java.util.Objects;

/**
 * Allows viewing a certain, fixed, number of elements in an underlying array. Mutating said elements is possible
 * (as {@link ArrayView#get(int)} returns references to element, but changing the reference itself is impossible (as no methods
 * such as {@code ArrayView#set} are provided.
 *
 * @param <E> The element type of the underlying array.
 */
public abstract class ArrayView<E> implements Iterable<E> {
	private final E[] array;

	/**
	 * Receives and initializes the common attributes between all types of array views (currently the underlying array itself).
	 *
	 * @param array The underlying array.
	 */
	ArrayView(final E[] array) {
		this.array = Objects.requireNonNull(array);
	}

	/**
	 * @return The underlying array.
	 */
	E[] getArray() {
		return this.array;
	}

	/**
	 * Stops further execution through throwing an exception if the provided {@code arrayIndex} is not a valid index, determined by
	 * {@link ArrayView#isValidIndex(int)}
	 *
	 * @param elementIndex The index of the element in the array view, not array.
	 */
	private void forceValidIndex(final int elementIndex) {
		if (!isValidIndex(elementIndex)) {
			throw new IllegalArgumentException("Attempted to get value at index " + elementIndex + " when the range of valid indices is 0 -> " + getArray().length);
		}
	}

	/**
	 * @param elementIndex The index of the element in the array view, not array.
	 * @return Whether {@code elementIndex} is an index that can is within the bounds of the array view.
	 */
	boolean isValidIndex(final int elementIndex) {
		return 0 <= elementIndex && elementIndex < size();
	}

	/**
	 * @return The number of elements that this ArrayView provides a view for.
	 */
	public abstract int size();


	abstract E getHelper(final int index);

	/**
	 * @param elementIndex The index of the element in the array view, not array.
	 * @return The element located at {@code elementIndex} in the array view, not array.
	 */
	public E get(final int elementIndex) {
		forceValidIndex(elementIndex);

		return getHelper(elementIndex);
	}

	public abstract ArrayViewIterator<E, ?> iterator();
}
