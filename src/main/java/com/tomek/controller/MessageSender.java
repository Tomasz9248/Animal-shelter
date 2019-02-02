package com.tomek.controller;

import java.io.Serializable;

public class MessageSender implements Serializable {

private MessageOrganizer messageOrganizer;

    private static final long serialUID = 123456789111L;

    public MessageSender(MessageOrganizer messageOrganizer) {
     this.messageOrganizer = messageOrganizer;
    }

    public void sendMessage(String messageSubject, String messageBody) {
        messageOrganizer.sendMessage(messageSubject, messageBody);
    }
}