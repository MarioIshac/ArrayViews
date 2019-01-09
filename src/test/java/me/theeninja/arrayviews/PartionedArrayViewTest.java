package me.theeninja.arrayviews;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PartionedArrayViewTest {
    @Test
    void test() {
        final Person[] personArray = Person.newPersonArray();

        final Partition[] partitions = {
            new Partition(0, 2),
            new Partition(2, personArray.length)
        };

        final PartitionedArrayView<Person> personArrayView = PartitionedArrayView.of(personArray, partitions);

        int elementIndexInArrayView = 0;

        for (Partition partition : partitions) {
            for (int elementIndexInPartition = 0;
                 elementIndexInPartition < partition.size();
                 elementIndexInPartition++, elementIndexInArrayView++) {
                final int elementIndexInArray = elementIndexInPartition + partition.getStart();

                final Person expectedElement = personArray[elementIndexInArray];
                final Person observedElement = personArrayView.get(elementIndexInArrayView);

                assertEquals(expectedElement, observedElement);
            }
        }
    }
}
