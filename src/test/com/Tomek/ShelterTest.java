package com.Tomek;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class ShelterTest {

    private Shelter shelter;
    private Cat cat;

    @Before
    public void setUp() {
        shelter = new Shelter();
        cat = new Cat("Puszek", "dachowiec", 4, false, true);
    }

    @Test
    public void shouldAllowAddAnimal() {
        shelter.addAnimal(cat);

        assertTrue(shelter.getAnimalList().contains(cat));
    }


    @Test
    public void shouldExecuteToStringFunction() {
        cat.toString();

        assertEquals("Name: Puszek, Race: dachowiec, Age: 4, Is outgoing: false, Uses little tray: true, Added: " + LocalDate.now(), cat.toString());
    }








}
