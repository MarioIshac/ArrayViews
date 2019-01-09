package me.theeninja.arrayviews;

public class GetterArrayViewIterator<E, AV extends ArrayView<E>> extends ArrayViewIterator<E, AV> {
    private int currentIndex;

    GetterArrayViewIterator(final AV arrayView) {
        super(arrayView);
    }

    private int getCurrentIndex() {
        return this.currentIndex;
    }

    private int incrementAndGetCurrentIndex() {
        return this.currentIndex++;
    }

    @Override
    public boolean hasNext() {
        return getCurrentIndex() != getArrayView().size();
    }

    @Override
    public E next() {
        final int newCurrentIndex = incrementAndGetCurrentIndex();

        return getArrayView().get(newCurrentIndex);
    }
}
