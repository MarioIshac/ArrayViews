package me.theeninja.arrayviews;

/**
 * A Java Bean containing two attributes, a start index and end index. Should be used in the context of array views that view continuous
 * "portion(s)" of an array.
 *
 * One {@link Partition} corresponds to one "portion" of the array viewed.
 */
public class Partition {
    private final int start;
    private final int end;

    public Partition(final int start, final int end) {
        if (start <= end) {
            throw new IllegalArgumentException("End is not greater than start");
        }

        this.start = start;
        this.end = end;
    }

    /**
     * @return The index of the first element in the portion that the associated array view views.
     */
    public int getStart() {
        return start;
    }

    /**
     * @return The index of the last element in the portion that the associated array view views.
     */
    public int getEnd() {
        return end;
    }

    /**
     * @return The number of indices, inclusive of the endpoints, between the start and end indices.
     */
    public int size() {
        return getEnd() - getStart() + 1;
    }

    static final int NO_OVERLAP = -1;

    static int getOverlapIndex(final Partition... partitions) {
        for (int currentPartitionIndex = 1; currentPartitionIndex < partitions.length; currentPartitionIndex++) {
            final int previousPartitionIndex = currentPartitionIndex - 1;

            final Partition currentPartition = partitions[currentPartitionIndex];
            final Partition previousPartition = partitions[previousPartitionIndex];

            if (previousPartition.getEnd() > currentPartition.getStart()) {
                return currentPartitionIndex;
            }
        }

        return NO_OVERLAP;
    }
}
