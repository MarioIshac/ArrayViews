package me.theeninja.arrayviews;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContiguousArrayViewTest {
    @Test
    void test() {
        final Person[] personArray = Person.newPersonArray();

        final Partition partition = new Partition(2, personArray.length - 2);
        final ContiguousArrayView<Person> personArrayView = ContiguousArrayView.of(personArray, partition);

        for (int indexInArray = partition.getStart(); indexInArray < partition.getEnd(); indexInArray++) {
            final int indexInArrayView = indexInArray - partition.getStart();

            final Person expectedElement = personArray[indexInArray];
            final Person observedElement = personArrayView.get(indexInArrayView);

            assertEquals(expectedElement, observedElement);
        }
    }
}
