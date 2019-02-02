package com.tomek.controller;

import com.tomek.controller.Shelter;
import com.tomek.model.Cat;
import com.tomek.model.Dog;

import java.util.Scanner;

public class ShelterApp {

    private final int ADD_NEW = 1;
    private final int REMOVE = 2;
    private final int SHOW_LIST = 3;
    private final int EXIT = 0;
    private Scanner sc = new Scanner(System.in);

    private Shelter shelter;

    public ShelterApp() {
        shelter = Shelter.readFromFile();
    }

    public void run() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("Choose your option:");
            System.out.println("1 - add new animal / 2 - remove adopted animal / 3 - show animals list / 0 - exit");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case ADD_NEW:
                    System.out.println("The animal is: cat/dog");
                    String animalType = sc.nextLine().trim().toLowerCase();
                    addAdnimalTypeOf(animalType);
                    break;
                case REMOVE:
                    System.out.println("Type animal's name:");
                    String name = sc.nextLine();
                    shelter.removeAnimal(name);
                    break;
                case SHOW_LIST:
                    System.out.println("1 - display dogs only / 2 - display cats only / 3 - display all");
                    int option = sc.nextInt();
                    promptAnimals(option);
                    break;
                case EXIT:
                    System.out.println("Exiting...");
                    shelter.saveToFile(shelter);
                    break;
            }
        }
    }

    private void addAdnimalTypeOf(String animalType) {
        if (!animalType.equals("cat") && (!animalType.equals("dog"))) {
            System.out.println("Choose one: cat or dog");
        } else {
            try {
                System.out.println("Type animal's name:");
                String name = sc.nextLine();
                System.out.println("Type race:");
                String race = sc.nextLine().toLowerCase();
                System.out.println("Type age:");
                int years = Integer.parseInt(sc.nextLine());
                if (animalType.equals("dog")) {
                    System.out.println("Is he/she fetching? (y/n)");
                    boolean aport = sc.nextLine().trim().toLowerCase().equals("y");
                    System.out.println("Is he/she purebred? (y/n)");
                    boolean purebred = sc.nextLine().trim().toLowerCase().equals("y");
                    shelter.addAnimal(new Dog(name, race, years, aport, purebred));
                }
                if (animalType.equals("cat")) {
                    System.out.println("Is he/she outgoing? (y/n)");
                    boolean outgoing = sc.nextLine().trim().toLowerCase().equals("y");
                    System.out.println("Does he/she use little tray? (y/n)");
                    boolean box = sc.nextLine().trim().toLowerCase().equals("y");
                    shelter.addAnimal(new Cat(name, race, years, outgoing, box));
                }
            } catch (IllegalArgumentException ne) {
                System.out.println("Something went wrong! Type an age as non negative integer number.");
            }
        }
    }

    private void promptAnimals(int option) {
        if (option == 1) {
            shelter.showDogsOnly();
        } else if (option == 2) {
            shelter.showCatsOnly();
        } else if (option == 3) {
            shelter.showStatus();
        } else {
            System.out.println("Wrong index. Try again.");
            run();
        }
    }
}