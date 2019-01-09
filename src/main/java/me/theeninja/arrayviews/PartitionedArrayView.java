package me.theeninja.arrayviews;

import java.util.Arrays;
import java.util.Comparator;

public class PartitionedArrayView<E> extends ArrayView<E> {
    private final Partition[] partitions;

    public static <E> PartitionedArrayView<E> of(final E[] array, final Partition... partitions) {
        return new PartitionedArrayView<>(array, partitions);
    }

    private PartitionedArrayView(final E[] array, final Partition... partitions) {
        super(array);

        final Comparator<Partition> partitionComparator = Comparator.comparing(Partition::getStart);

        Arrays.sort(partitions, partitionComparator);

        int overlapIndex = Partition.getOverlapIndex(partitions);
        boolean haveOverlap = overlapIndex != Partition.NO_OVERLAP;

        if (haveOverlap) {
            throw new IllegalArgumentException("Partitions at index " + overlapIndex);
        }

        this.partitions = partitions;
    }

    @Override
    public int size() {
        return Arrays.stream(getPartitions())
                     .mapToInt(Partition::size)
                     .sum();
    }

    @Override
    public E getHelper(final int indexInArrayView) {
        int totalCurrentSize = 0;

        for (final Partition partition : getPartitions()) {
            final int localSize = partition.size();

            if (indexInArrayView < totalCurrentSize + localSize) {
                final int indexInPartition = indexInArrayView - totalCurrentSize;
                final int indexInArray = partition.getStart() + indexInPartition;

                return getArray()[indexInArray];
            }

            totalCurrentSize += localSize;
        }

        throw new AssertionError("Range check should prohibit ever reaching this point.");
    }

    @Override
    public PartitionedArrayViewIterator<E> iterator() {
        return new PartitionedArrayViewIterator<>(this);
    }

    Partition[] getPartitions() {
        return partitions;
    }
}
