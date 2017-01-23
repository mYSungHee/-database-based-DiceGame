import java.util.Properties;
 
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class Mails {
    // 메일 관련 정보
    String host = "smtp.naver.com";
    final String username = "cisisn";
    final String password = "3co10dnjf**";
    int port=465;
    String recipient;
    String subject="인증번호.";;
    String body;
    public Mails(String mailadr){
    	this.recipient=mailadr;
    }
    public String mailSend(){     
        // 메일 내용
    	int rs=(int)(Math.random()*1000+9000);
        body = Integer.toString(rs);
         
        Properties props = System.getProperties();
         
         
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.trust", host);
          
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            String un=username;
            String pw=password;
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(un, pw);
            }
        });
       // session.setDebug(true); //for debug
          
        Message mimeMessage = new MimeMessage(session);
        try{
        mimeMessage.setFrom(new InternetAddress("cisisn@naver.com"));
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        mimeMessage.setSubject(subject);
        mimeMessage.setText(body);
        Transport.send(mimeMessage);
        }catch(MessagingException me){
        	me.printStackTrace();
        }
        return Integer.toString(rs);
    }
}