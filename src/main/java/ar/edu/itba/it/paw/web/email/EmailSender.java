package ar.edu.itba.it.paw.web.email;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EmailSender {
	public void sendEmail(String subject, String body){
		
		Properties emailProps = new Properties();
		InputStream systemResourceAsStream = getClass().getClassLoader()
				.getResourceAsStream("email.properties");
		try {
			emailProps.load(systemResourceAsStream);
		} catch (IOException e1) {
			return;
		}
			final String username = emailProps.getProperty("username");
			final String password = emailProps.getProperty("password");
		 	Properties props = new Properties();
		 	props.put("mail.smtp.auth", "true");
		 	props.put("mail.smtp.starttls.enable", "true");
		 	props.put("mail.smtp.host", emailProps.get("host"));
		 	props.put("mail.smtp.port", "587");
		 	
	        Session session = Session.getInstance(props, new Authenticator() {
	        	protected PasswordAuthentication getPasswordAuthentication(){
	        		return new PasswordAuthentication(username, password);
	        	}
			});


	        try {
	            Message msg = new MimeMessage(session);
	            msg.setFrom(new InternetAddress(emailProps.getProperty("from"),
	            		emailProps.getProperty("fromName")));
	            msg.setRecipients(Message.RecipientType.TO,
	                             InternetAddress.parse(emailProps.getProperty("to")));
	            msg.setSubject(subject);
	            msg.setText(body);
	            Transport.send(msg);

	        } catch (AddressException e) {
	            return;
	        } catch (MessagingException e) {
	            return;
	        } catch (UnsupportedEncodingException e) {
				return;
			}
	}
}
