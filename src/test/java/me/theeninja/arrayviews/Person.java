package me.theeninja.arrayviews;

public class Person {
    private final int age;
    private final String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    private static final int ALPHABET_SIZE = 'z' - 'a' + 1;

    static Person[] newPersonArray() {
        final Person[] persons = new Person[ALPHABET_SIZE];

        for (char letter = 'a'; letter <= 'z'; letter++) {
            final String personName = String.valueOf(letter);
            final int personAge = letter - 'a';

            final Person person = new Person(personAge, personName);

            // Person age serves as the person index for convenience
            persons[personAge] = person;
        }

        return persons;
    }
}
