package com.twoways.service;


import java.io.File;

import java.security.Security;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

public class GoogleTest {

    private static final String SMTP_HOST_NAME = "smtp.gmail.com";
    private static final String SMTP_PORT = "465";
    private static final String emailMsgTxt = "Test Message Contents";
    private static final String emailSubjectTxt = "A test from gmail";
    private static final String emailFromAddress = "twowaystest@gmail.com";
    private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    private static final String[] sendTo = 
    { "lucianonicolasfernandez@gmail.com" };

    /*
    public static void main(String[] args) throws Exception {

        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

        new GoogleTest().sendSSLMessage(sendTo, emailSubjectTxt, emailMsgTxt,
                                        emailFromAddress);
        System.out.println("Sucessfully Sent mail to All Users");
    }
*/

    public void sendSSLMessage(String[] recipients, String subject, 
                               String message, 
                               String from) throws MessagingException {
        boolean debug = true;

        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_HOST_NAME);
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.socketFactory.port", SMTP_PORT);
        props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.put("mail.smtp.socketFactory.fallback", "false");

        Session session = 
            Session.getDefaultInstance(props, new javax.mail.Authenticator() {

                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("twowaystest@gmail.com", 
                                                          "twowaystest123");
                    }
                });

        session.setDebug(debug);

        Message msg = new MimeMessage(session);
        InternetAddress addressFrom = new InternetAddress(from);
        msg.setFrom(addressFrom);

        InternetAddress[] addressTo = new InternetAddress[recipients.length];
        for (int i = 0; i < recipients.length; i++) {
            addressTo[i] = new InternetAddress(recipients[i]);
        }
        msg.setRecipients(Message.RecipientType.TO, addressTo);

        // Setting the Subject and Content Type
        msg.setSubject(subject);
        msg.setContent(message, "text/plain");
        Transport.send(msg);
    }


    public static void main(String[] args) {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", SMTP_HOST_NAME);
            props.put("mail.smtp.auth", "true");
            props.put("mail.debug", "true");
            props.put("mail.smtp.port", SMTP_PORT);
            props.put("mail.smtp.socketFactory.port", SMTP_PORT);
            props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
            props.put("mail.smtp.socketFactory.fallback", "false");

            Authenticator auth = new Authenticator() {

                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("twowaystest@gmail.com", 
                                                          "twowaystest123");
                    }
                };
            Session session = Session.getDefaultInstance(props, auth);
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("twowaystest@gmail.com"));
            msg.setSubject("Try attachment gmail");
            msg.setRecipient(Message.RecipientType.TO, 
                             new InternetAddress("lucianonicolasfernandez@gmail.com"));
            //add atleast simple body
            MimeBodyPart body = new MimeBodyPart();
            body.setText("Try attachment");
            //do attachment
            MimeBodyPart attachMent = new MimeBodyPart();
            FileDataSource dataSource = 
                new FileDataSource(new File("C:\\Documents and Settings\\Administrador\\Mis documentos\\Mis imágenes\\año nuevo-2011\\año nuevo-2011 005.jpg"));
            attachMent.setDataHandler(new DataHandler(dataSource));
            attachMent.setFileName("año nuevo-2011 005.jpg");
            attachMent.setDisposition(MimeBodyPart.ATTACHMENT);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(body);
            System.out.println("multipart");
            multipart.addBodyPart(attachMent);
            System.out.println("multipart2");
            msg.setContent(multipart);
            System.out.println("multipart3");
            Transport.send(msg);
            System.out.println("termino");
        } catch (AddressException ex) {
            ex.printStackTrace();
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }

}


