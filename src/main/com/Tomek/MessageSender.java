package com.Tomek;

import java.io.Serializable;

public class MessageSender implements Serializable {

private MessagePreparator messagePreparator;

    private static final long serialUID = 123456789111L;

    public MessageSender(MessagePreparator messagePreparator) {
     this.messagePreparator = messagePreparator;
    }

    public void sendMessage() {
        messagePreparator.getMessage();
    }
}
