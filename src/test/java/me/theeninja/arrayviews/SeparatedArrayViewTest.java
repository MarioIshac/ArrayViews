package me.theeninja.arrayviews;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SeparatedArrayViewTest {
    @Test
    void test() {
        final Person[] personArray = Person.newPersonArray();
        final int[] arrayIndices = {0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24};

        final SeparatedArrayView<Person> personArrayView = SeparatedArrayView.of(personArray, arrayIndices);

        for (int indexOfArrayIndex = 0; indexOfArrayIndex < arrayIndices.length; indexOfArrayIndex++) {
            final int arrayIndex = arrayIndices[indexOfArrayIndex];

            final Person expectedElement = personArray[arrayIndex];
            final Person observedElement = personArrayView.get(indexOfArrayIndex);

            assertEquals(expectedElement, observedElement);
        }
    }
}