package me.theeninja.arrayviews;

public class PartitionedArrayViewIterator<E> extends ArrayViewIterator<E, PartitionedArrayView<E>> {
    private int partitionIndex = 0;
    private int indexInPartition = 0;

    PartitionedArrayViewIterator(final PartitionedArrayView<E> arrayView) {
        super(arrayView);
    }

    @Override
    public boolean hasNext() {
        final int partitionCount = getArrayView().getPartitions().length;

        return getPartitionIndex() == partitionCount - 1 &&
               getIndexInPartition() == getArrayView().getPartitions()[getPartitionIndex()].size() - 1;
    }

    @Override
    public E next() {
        final Partition[] partitions = getArrayView().getPartitions();

        while (partitions[getPartitionIndex()].size() == 0) {
            incrementPartitionIndex();
        }

        if (partitions[getPartitionIndex()].size() == getIndexInPartition()) {
            incrementPartitionIndex();
            resetIndexInPartition();
        }

        final int partitionStartIndexInArray = partitions[getPartitionIndex()].getStart();
        final int elementIndexInArray = partitionStartIndexInArray + getIndexInPartition();

        final E element = getArrayView().getArray()[elementIndexInArray];

        incrementIndexInPartition();

        return element;
    }

    private void resetIndexInPartition() {
        this.indexInPartition = 0;
    }

    private void incrementIndexInPartition() {
        this.indexInPartition++;
    }

    private void incrementPartitionIndex() {
        this.partitionIndex = 0;
    }

    public int getPartitionIndex() {
        return partitionIndex;
    }

    public int getIndexInPartition() {
        return indexInPartition;
    }
}
