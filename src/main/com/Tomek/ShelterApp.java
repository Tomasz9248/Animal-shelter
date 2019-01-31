package com.Tomek;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;


public class ShelterApp {

    private final int ADD_NEW = 1;
    private final int REMOVE = 2;
    private final int SHOW_LIST = 3;
    private final int EXIT = 0;
    private Scanner sc = new Scanner(System.in);


    private Shelter shelter;

    public ShelterApp() {
        shelter = new Shelter();
        shelter = shelter.readFromFile();
    }

    public void run() {

        int choice = -1;
        while (choice != 0) {
            System.out.println('\n' + "Co chcesz zrobić?");
            System.out.println("1 - dodaj nowe zwierzę / 2 - usuń adoptowane zwierzę / 3 - pokaż listę zwierząt / 0 - wyjdź");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case ADD_NEW:
                    System.out.println("Zwierzę to: kot/pies");
                    String animalType = sc.nextLine().trim().toLowerCase();
                    addAdnimalTypeOf(animalType);
                    break;
                case REMOVE:
                    System.out.println("Podaj imię zwierzęcia:");
                    String name1 = sc.nextLine();
                    shelter.removeAnimal(name1);
                    break;
                case SHOW_LIST:
                    System.out.println("1 - pokaż psy / 2 - pokaż koty / 3 - pokaż wszystkie");
                    int option = sc.nextInt();
                    promptAnimals(option);
                    break;
                case EXIT:
                    System.out.println("Wychodzenie z systemu...");
                    shelter.saveToFile(shelter);
                    break;
            }
        }
    }

    private void addAdnimalTypeOf(String animalType) {
        if (!animalType.equals("kot") && (!animalType.equals("pies"))) {
            System.out.println("Wybierz zwierzę: kot lub pies");
        } else {
            try {
                System.out.println("Podaj imię:");
                String name = sc.nextLine();
                System.out.println("Podaj rasę:");
                String race = sc.nextLine().toLowerCase();
                System.out.println("Podaj wiek:");
                int years = Integer.parseInt(sc.nextLine());
                if (animalType.equals("pies")) {
                    System.out.println("Czy potrafi aportować? tak/nie");
                    boolean aport = sc.nextLine().trim().toLowerCase().equals("tak");
                    System.out.println("Czy ma rodowód? tak/nie");
                    boolean purebred = sc.nextLine().trim().toLowerCase().equals("tak");
                    shelter.addAnimal(new Dog(name, race, years, aport, purebred));
                }
                if (animalType.equals("kot")) {
                    System.out.println("Czy jest wychodzący? tak/nie");
                    boolean outgoing = sc.nextLine().trim().toLowerCase().equals("tak");
                    System.out.println("Czy potrafi korzystać z kuwety? tak/nie");
                    boolean box = sc.nextLine().trim().toLowerCase().equals("tak");
                    shelter.addAnimal(new Cat(name, race, years, outgoing, box));
                }
            } catch (IllegalArgumentException ne) {
                System.out.println("Dodawanie niepomyślne." +
                        '\n' +"Podaj wiek w formie pełnej liczby nieujemnej.");
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
            System.out.println("Wybrałeś wartość spoza zakresu. Spróbuj ponownie");
            run();
        }
    }

}

