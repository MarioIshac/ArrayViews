package me.theeninja.arrayviews;

import java.util.Iterator;

/**
 * An iterator specialized for array views.
 *
 * @param <E> The element type of the array view.
 * @param <AV> The array view type itself (any subclass of {@link ArrayView}.
 */
public abstract class ArrayViewIterator<E, AV extends ArrayView<E>> implements Iterator<E> {
	private final AV arrayView;

	/**
	 * @param arrayView The array view to be iterated over.
	 */
	ArrayViewIterator(final AV arrayView) {
		this.arrayView = arrayView;
	}

	/**
	 * @return The array view to be iterated over.
	 */
	AV getArrayView() {
		return arrayView;
	}
}
