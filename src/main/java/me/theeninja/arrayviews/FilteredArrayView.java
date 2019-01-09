package me.theeninja.arrayviews;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class FilteredArrayView<E> extends SeparatedArrayView<E> {
    private static <E> int[] newArrayIndices(final E[] array, final Predicate<? super E> elementPredicate) {
        return IntStream.range(0, array.length)
                        .filter(elementIndex -> {
                            final E element = array[elementIndex];
                            return elementPredicate.test(element);
                        })
                        .toArray();
    }

    public static <E> FilteredArrayView<E> of(final E[] array, final Predicate<? super E> elementPredicate) {
        return new FilteredArrayView<>(array, elementPredicate);
    }

    private FilteredArrayView(final E[] array, final Predicate<? super E> elementPredicate) {
        super(array, newArrayIndices(array, elementPredicate));
    }
}
