package com.SpringBootEmailing.service;

import com.SpringBootEmailing.entities.UserEntity;

public interface UserService {
    UserEntity saveUser(UserEntity user);
    UserEntity findUserByMail(String email);
    Boolean verifyToken(String token);
}
