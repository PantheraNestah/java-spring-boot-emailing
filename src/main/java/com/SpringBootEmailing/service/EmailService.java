package com.SpringBootEmailing.service;

public interface EmailService {
    void sendSimpleEmail(String to, String subject, String token);
    void sendSimpleMailWithAttachment(String to, String subject, String content);
    void sendSimpleMailEmbed(String to, String subject, String content);
    void sendHtmlMail(String to, String subject);
}
