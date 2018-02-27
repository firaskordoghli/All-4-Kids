/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.service;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author casa-net
 */
public class ServiceMail {
   /* public static void sendMail(String to,String subject,String message) throws MessagingException
    {
  String host="smtp.gmail.com";  
  final String user="r4.cherif@gmail.com";//change accordingly  
  final String password="21945026";//change accordingly  
        
  
   //Get the session object  
    Properties props = new Properties();

    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.from",user);
    props.put("mail.smtp.starttls.enable", true);
    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");  

    props.setProperty("mail.debug", "true");

    Session session = Session.getInstance(props, null);
    MimeMessage msg = new MimeMessage(session);

    msg.setRecipients(Message.RecipientType.TO, to);
    msg.setSubject(subject);
    msg.setSentDate(new Date());
    msg.setText(message);

    Transport transport = session.getTransport("smtp");

    transport.connect(user, password);
    transport.sendMessage(msg, msg.getAllRecipients());
    transport.close();
  
   

    }*/
    
    
     static Properties mailServerProperties;
	static  Session getMailSession;
	static  MimeMessage generateMailMessage;
        
       
	
 
	public static void generateAndSendEmail(String Titre,String Contenu,int Reponse,String sender ) throws AddressException, MessagingException {
              
		
		System.out.println("\n 1st ===> setup Mail Server Properties..");
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		System.out.println("Mail Server Properties have been setup successfully..");
 
		
		System.out.println("\n\n 2nd ===> get Mail Session..");
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(sender));
		generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(sender));
		generateMailMessage.setSubject(Titre);
		String emailBody = "Salutation <br> suite a votre rèclamation ci-dessous :" + "<br> "+Contenu+"<br><br><br>Notre reponse : "+Reponse+ "<br><br><Merci pour votre comprehension ";
		generateMailMessage.setContent(emailBody, "text/html");
		System.out.println("Mail Session has been created successfully..");
 
		
		System.out.println("\n\n 3rd ===> Get Session and Send mail");
		Transport transport = getMailSession.getTransport("smtp");
 
		
		transport.connect("smtp."
                        + "gmail.com", "fatnassi.salmen.fs@gmail.com", "FS24285612");
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();
	}
    
    
}
