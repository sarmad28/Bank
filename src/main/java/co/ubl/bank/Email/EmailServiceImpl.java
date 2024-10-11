package co.ubl.bank.Email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private JavaMailSender javaMailSender;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Value("${spring.mail.username}")
    private String senderEmail;

    @Override
    public void sendEmailAlert(EmailDTO emailDTO) {
        // Implementation for sending email alert
        // Example: JavaMail API, SMTP, or external email service
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setSubject(mail.getSubject());
            mail.setFrom(senderEmail);
            mail.setTo(emailDTO.getRecipient());
            mail.setText(emailDTO.getMessage());
            javaMailSender.send(mail);
        } catch (MailException ex) {
            throw new RuntimeException(ex);
        }
    }
}
