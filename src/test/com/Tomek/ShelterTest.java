package com.Tomek;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.time.LocalDate;

public class ShelterTest {

    private Shelter shelter;
    private Cat cat;
    private Cat cat2;

    @Before
    public void setUp() {
        shelter = new Shelter();
        cat = new Cat("Puszek", "dachowiec", 4, false, true);
        cat2 = new Cat("Puszek", "dachowiec", 4, false, true);
    }

    @Test
    public void shouldCreateNewAnimal() {

        Animal animal = new Animal("Name", "defaultRace", 5);

        assertEquals(animal, new Animal("Name", "defaultRace", 5));
    }

    @Test
    public void shouldntAcceptNegativeAge() {

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Animal("Name", "default", -5)
        );

        assertEquals("Wiek nie może być wartością ujemną!", exception.getMessage());

    }

    @Test
    public void shouldExecuteToStringFunction() {
        cat.toString();

        assertEquals("Name: Puszek, Race: dachowiec, Age: 4, Is outgoing: false, Uses little tray: true, Added: " + LocalDate.now(), cat.toString());
    }

    @Test
    public void shouldDeclareObjectsAreEqual() {

        assertTrue(cat.equals(cat2));
    }

    @Test
    public void shouldDeclareObjectsAreDifferent() {

        Cat cat3 = new Cat("DifferentName", "race", 7, true, false);

        assertFalse(cat.equals(cat3));
    }

    @Test
    public void shouldAllowAddAnimal() {
        shelter.addAnimal(cat);

        assertTrue(shelter.getAnimalList().contains(cat));
    }


}
