package model.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService {

/*Envoyer un e-mail a partir de la page index pour demander des informations */
	
		public  void sendEmails( 
				String subject, String content) {

			final String username = "benaddisoukaina@gmail.com";
			final String password = "benaddiyassine1986";
			
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.googlemail.com");
			props.put("mail.smtp.port", "587");
            
			
			Session session = Session.getInstance(props,
			  new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			  });

			try {
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("benaddisoukaina@gmail.com"));
				message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("benaddisoukaina@gmail.com"));
				
				message.setSubject(subject);
				message.setText(content);

				Transport.send(message);

				System.out.println("Done");

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		}
	}
	