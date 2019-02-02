package com.tomek.controller;

import com.tomek.model.Animal;
import com.tomek.model.Cat;
import com.tomek.model.Dog;
import com.tomek.model.EmailProperties;

import java.io.*;
import java.util.*;


public class Shelter implements Serializable {

    private final int SHELTER_CAPACITY = 15;
    private int currentAnimalsNumber;
    private List<Animal> animalList;
    private MessageSender emailSender;

    private static final long serialUID = 123456789111L;
    private static final String shelterTxtFile = "shelter.txt";

    public Shelter() {
        currentAnimalsNumber = 0;
        animalList = new ArrayList<>();
        emailSender = new MessageSender(new EmailMessageOrganizer());
    }

    protected void saveToFile(Object obj) {
        File shelterFile = new File(shelterTxtFile);
        try (
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(shelterFile))
        ) {
            oos.writeObject(obj);
        } catch (IOException ioe) {
            System.out.println("Couldn't save file.");
            ioe.printStackTrace();
        }
    }

    protected static Shelter readFromFile() {
        Shelter shelter;
        try (
                ObjectInputStream oos = new ObjectInputStream(new FileInputStream(shelterTxtFile))
        ) {
            shelter = (Shelter) oos.readObject();
        } catch (IOException | ClassNotFoundException e) {
            shelter = new Shelter();
        }
        return shelter;
    }

   public void addAnimal(Animal animal) {
        if (!isShelterFull()) {
            animalList.add(animal);
            currentAnimalsNumber++;
            if (SHELTER_CAPACITY - currentAnimalsNumber < 12) {
                emailSender.sendMessage(EmailProperties.FULL_SHELTER_SUBJECT, EmailProperties.FULL_SHELTER_BODY);
            }
        } else {
            System.out.println("We're sorry. The shelter is full and we unfortunately can't receive more animals.");
        }
    }

    protected void removeAnimal(String name) {
        animalList.removeIf(animal -> animal.getName().equals(name));
        System.out.println(name + " found home!");
    }

    protected void showStatus() {
        animalList.forEach(System.out::println);
        printCapacityCommunicate();
    }

    protected void showDogsOnly() {
        animalList.stream()
                .filter(animal -> animal instanceof Dog)
                .forEach(System.out::println);
    }

    protected void showCatsOnly() {
        animalList.stream()
                .filter(animal -> animal instanceof Cat)
                .forEach(System.out::println);
    }

    private void printCapacityCommunicate() {
        if (isShelterFull()) {
            System.out.println("The shelter is full. Cant receive more animals.");
        } else if (currentAnimalsNumber / SHELTER_CAPACITY * 100 >= 75) {
            System.out.println("The shelter is almost full.");
        } else {
            System.out.println("The shelter has plenty of free space.");
        }
    }

    private boolean isShelterFull() {
        return (currentAnimalsNumber >= SHELTER_CAPACITY);
    }

    private int getSHELTER_CAPACITY() {
        return SHELTER_CAPACITY;
    }

    private int getCurrentAnimalsNumber() {
        return currentAnimalsNumber;
    }

    private void setCurrentAnimalsNumber(int currentAnimalsNumber) {
        this.currentAnimalsNumber = currentAnimalsNumber;
    }

    public List<Animal> getAnimalList() {
        return animalList;
    }

    private void setAnimalList(List<Animal> animalList) {
        this.animalList = animalList;
    }
}