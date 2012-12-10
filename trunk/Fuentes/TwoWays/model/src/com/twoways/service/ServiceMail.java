package com.twoways.service;

import com.twoways.to.OrdersDocsTO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.net.URL;

import java.security.Security;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import javax.mail.Address;
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


public class ServiceMail {

    private static final String SMTP_HOST_NAME = "smtp.gmail.com";
    private static final String SMTP_PORT = "465";
    private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
   // private static final String TMP_DIR_PATH = "C:\\WINDOWS\\Temp";
    private static final String TMP_DIR_PATH = System.getProperty("java.io.tmpdir");
    private final String CHARACTER_SET = "windows-1252";

    private String[] sendTo = new String[1];

    ResourceBundle rb = ResourceBundle.getBundle("twoways");
    String userMail = rb.getString("userMail");
    String userMailPassword = rb.getString("userMailPassword");
    String userMailSender = rb.getString("userMailSender");
    
    //"/home/agustina/Documentos/Twoways/Querylog/Querylog.doc";
    String atattachObligatorio =  "/home/resources/Querylog.doc";
    //String atattachObligatorio = rb.getString("attachdoc");
    
    public static void main(String[] args) throws Exception {

        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        String [] sender={"nicomarjanov@gmail.com"};  
        new ServiceMail().sendSSLMessage(sender, "subject", "juj",
                                         "projects@twoways.net");
        System.out.println("Sucessfully Sent mail to All Users");
    }


    public void sendSSLMessage(String[] recipients, String subject, 
                               String message, 
                               String from) throws MessagingException {
        boolean debug = true;
        setproxy();

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
                        return new PasswordAuthentication(userMail, 
                                                          userMailPassword);
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
        msg.setContent(message, "text/html");
        Transport.send(msg);
    }


    public void sendAttach(String toMail, List<OrdersDocsTO> ordDocList, 
                    String subject, String otrosDestinatarios, String message)  throws Exception {
        try {

            setproxy();

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
                        return new PasswordAuthentication(userMail, 
                                                          userMailPassword);
                    }
                };
            Session session = Session.getDefaultInstance(props, auth);
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(userMailSender));
            Address[] cc = null;
            if (otrosDestinatarios != null && !otrosDestinatarios.equalsIgnoreCase("")){
                String otros[] = otrosDestinatarios.split(",");
                cc = new InternetAddress[otros.length];
                int i=0;
                for(String dir : otros){
                    if (!dir.equalsIgnoreCase(""))
                        cc[i++]=new  InternetAddress(dir);
                }
            }
            
            msg.setSubject(subject,CHARACTER_SET);
            msg.setRecipient(Message.RecipientType.TO, 
                             new InternetAddress(toMail));
            if (cc != null)
                msg.setRecipients(Message.RecipientType.CC,cc);   
                
            //Enviar una copia en bcc a la direccion de projects@twoways.net
            msg.setRecipients(Message.RecipientType.BCC,userMailSender);       
            
            //add atleast simple body
            MimeBodyPart body = new MimeBodyPart();
            body.setText(message);
            body.setContent(message,"text/html");
           
            //do attachment
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(body);
            Map docs= new HashMap();
            
             
            
            if (atattachObligatorio != null && atattachObligatorio.length() > 0){
                MimeBodyPart attachMent = new MimeBodyPart();
               
                String strFilePath = atattachObligatorio;
                System.out.println("Archivo querylog: "+strFilePath);
                FileDataSource dataSource = 
                    new FileDataSource(new File(strFilePath));
                attachMent.setDataHandler(new DataHandler(dataSource));
                attachMent.setFileName(atattachObligatorio.substring(atattachObligatorio.lastIndexOf("/")+1));
                attachMent.setDisposition(MimeBodyPart.ATTACHMENT);
                System.out.println("multipart");
                multipart.addBodyPart(attachMent);
                
            }
                        
            for(OrdersDocsTO ordDoc : ordDocList){
                
                if (docs.get(ordDoc.getOdoId()) == null){
               
                docs.put(ordDoc.getOdoId(),ordDoc.getOdoId()); 
                MimeBodyPart attachMent = new MimeBodyPart();
                convertTOFile(ordDoc.getOdoName(),ordDoc.getOdoDoc()); 
                String strFilePath = TMP_DIR_PATH + "/" + ordDoc.getOdoName();
    
                System.out.println("Archivo temporal: "+strFilePath);
                FileDataSource dataSource = 
                    new FileDataSource(new File(strFilePath));
                attachMent.setDataHandler(new DataHandler(dataSource));
                attachMent.setFileName(ordDoc.getOdoName());
                attachMent.setDisposition(MimeBodyPart.ATTACHMENT);
                System.out.println("multipart");
                multipart.addBodyPart(attachMent);
                
                }
                
            }           
           
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


    public static void setproxy() {
       /* System.setProperty("java.net.useSystemProxies", "true");
        System.out.println("detecting proxies");
        List l = null;
        try {
            l = ProxySelector.getDefault().select(new URI("http://foo/bar"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (l != null) {
            for (Iterator iter = l.iterator(); iter.hasNext(); ) {
                java.net.Proxy proxy = (java.net.Proxy)iter.next();
                System.out.println("proxy hostname : " + proxy.type());
                InetSocketAddress addr = (InetSocketAddress)proxy.address();
                if (addr == null) {
                    System.out.println("No Proxy");
                } else {
                    System.out.println("proxy hostname : " + 
                                       addr.getHostName());
                    System.setProperty("http.proxyHost", addr.getHostName());
                    System.out.println("proxy port : " + addr.getPort());
                    System.setProperty("http.proxyPort", 
                                       Integer.toString(addr.getPort()));
                }
            }
        }*/
    }

    public void convertTOFile(String fileName, byte[] file ) {


        String strFilePath = TMP_DIR_PATH + "/" + fileName;


        try

        {

            FileOutputStream fos = new FileOutputStream(strFilePath);
            fos.write(file);

            fos.close();
           


        } catch (FileNotFoundException ex)

        {

            System.out.println("FileNotFoundException : " + ex);

        }

        catch (IOException ioe)

        {

            System.out.println("IOException : " + ioe);

        }


    }


   
}


