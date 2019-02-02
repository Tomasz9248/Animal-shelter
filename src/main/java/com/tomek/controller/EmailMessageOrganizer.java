package com.tomek.controller;

import com.tomek.model.EmailProperties;

import java.io.Serializable;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailMessageOrganizer implements MessageOrganizer, Serializable {

    private Properties emailProperties;
    private Session mailSession;
    private MimeMessage emailMessage;

    private static final long serialUID = 123456789111L;

    @Override
    public void sendMessage(String emailSubject, String emailBody) {
    setMailServerProperties();
    createEmailMessage(emailSubject, emailBody);
    sendEmail();
    }

    private void setMailServerProperties() {
        emailProperties = System.getProperties();
        emailProperties.put("mail.smtp.port", EmailProperties.GMAIL_PORT);
        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.starttls.enable", "true");
    }

   private void createEmailMessage(String emailSubject, String emailBody) {
        try {
            String[] toEmails = {"tomasz.development@gmail.com"};

            mailSession = Session.getDefaultInstance(emailProperties, null);
            emailMessage = new MimeMessage(mailSession);

            for (int i = 0; i < toEmails.length; i++) {
                emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmails[i]));
            }
            emailMessage.setSubject(emailSubject);
            emailMessage.setText(emailBody);
        } catch (MessagingException me) {
            me.printStackTrace();
        }
    }

    private void sendEmail() {
        try {
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(EmailProperties.GMAIL_HOST, EmailProperties.SHELTER_EMAIL_ADRESS, EmailProperties.SHELTER_EMAIL_PASSWORD);
            transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
            transport.close();
            System.out.println("Email sent successfully.");
        } catch (MessagingException me) {
            me.printStackTrace();
        }
    }
}