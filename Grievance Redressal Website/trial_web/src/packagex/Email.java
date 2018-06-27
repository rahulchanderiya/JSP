package packagex;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

class mail {
	
	public  void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
	    Properties props = System.getProperties();
	  String host = "smtp.gmail.com";
	   // String host="localhost";
	  // props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	    props.put("mail.smtp.starttls.enable", "true");
	   // props.put("mail.smtp.host", host);
	   props.put("mail.smtp.ssl.trust", host);
	    props.put("mail.smtp.user", from);
	    props.put("mail.smtp.password", pass);
	    props.put("mail.smtp.port", "587");//587
	    props.put("mail.smtp.auth", "true");
	   //System.out.println("success point 1");

	    Session session = Session.getDefaultInstance(props);
	    MimeMessage message = new MimeMessage(session);

	    try {
	         //System.out.println("success point 2");

	        message.setFrom(new InternetAddress(from));
	        InternetAddress[] toAddress = new InternetAddress[to.length];

	        // To get the array of addresses
	        for( int i = 0; i < to.length; i++ ) {
	            toAddress[i] = new InternetAddress(to[i]);
	        }

	        for( int i = 0; i < toAddress.length; i++) {
	            message.addRecipient(Message.RecipientType.TO, toAddress[i]);
	        }

	         //System.out.println("success point 3");

	        message.setSubject(subject);
	        message.setText(body);
	        // System.out.println("success point 4");

	        Transport transport = session.getTransport("smtp");
	        // System.out.println("success point 5");

	        transport.connect(host, from, pass);
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	        //System.out.println("success 6");
	    }
	    catch (AddressException ae) {
	        ae.printStackTrace();
	    }
	    catch (MessagingException me) {
	        me.printStackTrace();
	    }
	  }
}


public class Email {

	public mail sendmail;
	
	public Email() {
		sendmail = new mail();
	}
	
	/*public static void main(String[] args) {
		mail m = new mail();
		String from = "dbms11project@gmail.com";
		String pass = "rahul!123";
		String[] to = { "rahulchanderiya@gmail.com" }; // list of recipient email addresses
		String subject = "Java send mail example";
		String body = "Welcome to JavaMail!";
		m.sendFromGMail(from, pass, to, subject, body);
	
	}*/

}
