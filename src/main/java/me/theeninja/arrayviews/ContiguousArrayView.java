package me.theeninja.arrayviews;

public class ContiguousArrayView<E> extends ArrayView<E> {
	private final Partition partition;

	public static <E> ContiguousArrayView<E> of(final E[] array, final Partition partition) {
		return new ContiguousArrayView<>(array, partition);
	}

	private ContiguousArrayView(final E[] array, final Partition partition) {
		super(array);

		this.partition = partition;
	}

	@Override
	boolean isValidIndex(final int elementIndex) {
		return 0 <= elementIndex && elementIndex < size();
	}

	@Override
	public int size() {
		return getPartition().size();
	}

	@Override
	public E getHelper(final int relativeIndex) {
		final int partitionStart = getPartition().getStart();
		final int absoluteIndex = relativeIndex + partitionStart;

		return getArray()[absoluteIndex];
	}

	@Override
	public GetterArrayViewIterator<E, ContiguousArrayView<E>> iterator() {
		return new GetterArrayViewIterator<>(this);
	}

	public Partition getPartition() {
		return partition;
	}
}
