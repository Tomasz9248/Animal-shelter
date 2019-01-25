package com.Tomek;


import java.io.Serializable;
import java.time.LocalDate;

public class Animal implements Comparable<Animal>, Serializable {

    private String name;
    private String race;
    private int age;
    private LocalDate additionDate = LocalDate.now();

    private static final long serialUID = 123456789111L;

    public Animal() {
    }

    public Animal(String name, String race, int age) {
        this.race = race;
        this.name = name;
        this.age = age;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public int compareTo(Animal o) {
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: " + name + ", ");
        sb.append("Race: " + race + ", ");
        sb.append("Age: " + age + ", ");
        return sb.toString();
    }
}
