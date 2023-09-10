package com.SpringBootEmailing.service;

public interface EmailService {
    void sendSimpleEmail(String name, String to, String token);
    void sendSimpleMailWithAttachment(String name, String to, String content);
    void sendSimpleMailEmbed(String name, String to, String content);
    void sendHtmlMail(String name, String to, String token);
    void sendHtmlMailWithAttachment(String name, String to, String token);
}
