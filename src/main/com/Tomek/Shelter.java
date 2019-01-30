package com.Tomek;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class Shelter implements Serializable {

    private final int SHELTER_CAPACITY = 15;
    private int currentAnimalsNumber;
    private List<Animal> animalList;
    private MessageSender emailSender;

    private static final long serialUID = 123456789111L;


    public Shelter() {
        currentAnimalsNumber = 0;
        animalList = new ArrayList<>();
emailSender  = new MessageSender(new EmailMessagePreparator());
    }

    protected void saveToFile(Object obj) {
        File shelterFile = new File("shelter.txt");

        try (
                FileOutputStream fos = new FileOutputStream(shelterFile);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {

            oos.writeObject(obj);

        } catch (IOException ioe) {
            System.out.println("Couldn't save file.");
            ioe.printStackTrace();
        }
    }

    protected Shelter readFromFile() {
        Shelter shelter = new Shelter();
        try (
                FileInputStream fis = new FileInputStream("shelter.txt");
                ObjectInputStream oos = new ObjectInputStream(fis);
        ) {
            shelter = (Shelter) oos.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return shelter;
    }

    protected void addAnimal(Animal animal) {
        if (!isShelterFull()) {
            animalList.add(animal);
            currentAnimalsNumber++;
            if (SHELTER_CAPACITY - currentAnimalsNumber < 5) {
                emailSender.sendMessage();
            }
        } else {
            System.out.println("We're sorry. The shelter is full and we unfortunately can't receive more animals.");
        }
    }

    protected void removeAnimal(String name) {
        animalList.removeIf(animal -> animal.getName().equals(name));
        System.out.println(name + " znalazł dom");
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
        }
        else if (currentAnimalsNumber / SHELTER_CAPACITY * 100 >= 75) {
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
