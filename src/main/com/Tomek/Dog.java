package com.Tomek;

import java.time.LocalDate;

public class Dog extends Animal {


    private boolean isFetching;
    private boolean isPurebred;
    private LocalDate additionDate;

    private static final long serialUID = 123456789111L;


    public Dog(String name, String race, int age, boolean isFetching, boolean isPurebred) {
        super(name, race, age);
        this.isFetching = isFetching;
        this.isPurebred = isPurebred;
        additionDate = LocalDate.now();
    }

    public boolean isFetching() {
        return isFetching;
    }

    public void setFetching(boolean fetching) {
        isFetching = fetching;
    }

    public boolean isPurebred() {
        return isPurebred;
    }

    public void setPurebred(boolean purebred) {
        isPurebred = purebred;
    }

    @Override
    public int compareTo(Animal o) {
        return super.compareTo(o);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("Fetching: " + isFetching + ", ");
        sb.append("Purebred: " + isPurebred + ", ");
        sb.append("Added: " + additionDate);
        return sb.toString();
    }
}
