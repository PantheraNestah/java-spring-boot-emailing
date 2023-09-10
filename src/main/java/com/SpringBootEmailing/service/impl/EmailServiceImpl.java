package com.SpringBootEmailing.service.impl;

import com.SpringBootEmailing.service.EmailService;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.BodyPart;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.util.Map;

import static com.SpringBootEmailing.utility.EmailUtility.createVerificationUrl;

@Service
@Data
public class EmailServiceImpl implements EmailService {
    public static final String NEW_USER_ACCOUNT_VERIFICATION = "New User Account Verification";
    @Value("${verify_email_host}")
    private String host;
    @Value("${spring.mail.username}")
    private String from;
    @Autowired
    private final JavaMailSender mailSender;
    @Autowired
    private TemplateEngine templateEngine;
    @Override
    @Async
    public void sendSimpleEmail(String name, String to, String token) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            message.setFrom(from);
            message.setTo(to);
            String str = "Hello " + name + ".\n\nYour new account was Successfully created.\nClick link below to verify.\n\n" + host + "/api/users?token=" + token;
            message.setText(str);
            mailSender.send(message);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }

    @Override
    @Async
    public void sendSimpleMailWithAttachment(String name, String to, String token) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setPriority(1);
            helper.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            helper.setFrom(from);
            helper.setTo(to);
            String str = "Hello " + name + ".\n\nYour new account was Successfully created.\nClick link below to verify.\n\n" + host + "/api/users?token=" + token;
            helper.setText(str);
            FileSystemResource file = new FileSystemResource(new File(System.getProperty("user.home") + "/Pictures/toProfile.jpg"));
            helper.addAttachment(file.getFilename(), file);
            mailSender.send(message);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    @Async
    public void sendSimpleMailEmbed(String name, String to, String token) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setPriority(1);
            helper.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            helper.setFrom(from);
            helper.setTo(to);
            String str = "Hello " + name + ".\n\nYour new account was Successfully created.\nClick link below to verify.\n\n" + host + "/api/users?token=" + token;
            helper.setText(str);
            FileSystemResource file = new FileSystemResource(new File(System.getProperty("user.home") + "/Pictures/toProfile.jpg"));
            helper.addInline("<" + file.getFilename() + ">", file);
            mailSender.send(message);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    @Async
    public void sendHtmlMail(String name, String to, String token) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setPriority(1);
            helper.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            helper.setFrom(from);
            helper.setTo(to);
            Context ctx = new Context();
            ctx.setVariables(Map.of("name", name, "url", createVerificationUrl(token, host)));
            String text = templateEngine.process("emailTemplate.html", ctx);
            helper.setText(text, true);
            mailSender.send(message);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    @Override
    @Async
    public void sendHtmlMailWithAttachment(String name, String to, String token){
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setPriority(1);
            helper.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            helper.setFrom(from);
            helper.setTo(to);
            Context ctx = new Context();
            ctx.setVariables(Map.of("name", name, "url", createVerificationUrl(token, host)));
            String text = templateEngine.process("emailTemplate.html", ctx);
            //helper.setText(text, true);

            MimeMultipart mimeMultipart = new MimeMultipart("related");
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(text,"text/html");
            mimeMultipart.addBodyPart(messageBodyPart);

            //adding image to email body
            BodyPart imageBodyPart = new MimeBodyPart();
            DataSource dataSource = new FileDataSource(System.getProperty("user.home") + "/Pictures/toProfile.jpg");
            imageBodyPart.setDataHandler(new DataHandler(dataSource));
            imageBodyPart.setHeader("Content-ID", "image");
            mimeMultipart.addBodyPart(imageBodyPart);

            message.setContent(mimeMultipart);

            mailSender.send(message);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
