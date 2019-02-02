package com.tomek.model;

import java.io.Serializable;

public class EmailProperties implements Serializable {

    public static final String SHELTER_EMAIL_ADRESS = "savoirself";
    public static final String SHELTER_EMAIL_PASSWORD = "testPassword";
    public static final String GMAIL_PORT = "587";
    public static final String GMAIL_HOST = "smtp.gmail.com";
    public static final String FULL_SHELTER_SUBJECT = "Warning! Java shelter is almost full!";
    public static final String FULL_SHELTER_BODY = "Dear collagues '\n' We have taken on a shelter new pet. Current number of free slots is less than 5. Please be aware of that.";
}
