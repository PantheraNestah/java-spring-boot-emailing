package com.SpringBootEmailing.service.impl;

import com.SpringBootEmailing.service.EmailService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {
    @Override
    public void sendSimpleEmail(String to, String subject, String token) {

    }

    @Override
    public void sendSimpleMailWithAttachment(String to, String subject, String content) {

    }

    @Override
    public void sendSimpleMailEmbed(String to, String subject, String content) {

    }

    @Override
    public void sendHtmlMail(String to, String subject) {

    }
}
