package com.SpringBootEmailing.service.impl;

import com.SpringBootEmailing.entities.ConfirmationEntity;
import com.SpringBootEmailing.entities.UserEntity;
import com.SpringBootEmailing.repository.ConfirmationRepository;
import com.SpringBootEmailing.repository.UserRepository;
import com.SpringBootEmailing.service.EmailService;
import com.SpringBootEmailing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private ConfirmationRepository tokenRepository;
    @Autowired
    private EmailService emailService;

    @Override
    public UserEntity saveUser(UserEntity user) {
        user.setIsEnabled(false);
        ConfirmationEntity confirmation = new ConfirmationEntity(user);
        tokenRepository.save(confirmation);

        /**
         * send mail for verification
         */
        //emailService.sendSimpleEmail(user.getName(), user.getEmail(), confirmation.getToken());
        //emailService.sendSimpleMailWithAttachment(user.getName(), user.getEmail(), confirmation.getToken());
        //emailService.sendSimpleMailEmbed(user.getName(), user.getEmail(), confirmation.getToken());
        emailService.sendHtmlMail(user.getName(), user.getEmail(), confirmation.getToken());

        return (repository.save(user));
    }

    @Override
    public UserEntity findUserByMail(String email) {
        return (repository.findByEmail(email));
    }
    @Override
    public Boolean verifyToken(String token)
    {
        ConfirmationEntity confirmation = tokenRepository.findByToken(token);
        UserEntity user = repository.findByEmail(confirmation.getUser().getEmail());
        user.setIsEnabled(true);
        repository.save(user);
        return (Boolean.TRUE);
    }
}
