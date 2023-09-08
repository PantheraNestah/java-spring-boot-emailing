package com.SpringBootEmailing.service.impl;

import com.SpringBootEmailing.repository.ConfirmationRepository;
import com.SpringBootEmailing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl {
    @Autowired
    private UserRepository repository;
    @Autowired
    private ConfirmationRepository tokenRepository;
}
