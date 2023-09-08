package com.SpringBootEmailing.service;

import com.SpringBootEmailing.entities.ConfirmationEntity;
import com.SpringBootEmailing.entities.UserEntity;

public interface UserService {
    public UserEntity saveUser(UserEntity user);
    public UserEntity findUserByMail(String email);
    public ConfirmationEntity findConfirmationToken(String token);
}
