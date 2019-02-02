package com.tomek.model;

import java.time.LocalDate;

public class Cat extends Animal {

    private boolean isOutgoing;
    private boolean isUsingLittleTray;
    private LocalDate additionDate;

    private static final long serialUID = 123456789111L;

    public Cat(String name, String race, int age, boolean isOutgoing, boolean isUsingLittleTray) {
        super(name, race, age);
        this.isOutgoing = isOutgoing;
        this.isUsingLittleTray = isUsingLittleTray;
        additionDate = LocalDate.now();
    }

    public boolean isOutgoing() {
        return isOutgoing;
    }

    public void setOutgoing(boolean outgoing) {
        isOutgoing = outgoing;
    }

    public boolean isUsingLittleTray() {
        return isUsingLittleTray;
    }

    public void setUsingLittleTray(boolean usingLittleTray) {
        isUsingLittleTray = usingLittleTray;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("Is outgoing: " + isOutgoing + ", ");
        sb.append("Uses little tray: " + isUsingLittleTray + ", ");
        sb.append("Added: " + additionDate);
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (isOutgoing? 1:0);
        result = 31 * result + (isUsingLittleTray()? 1:0);
        return result;
    }
}