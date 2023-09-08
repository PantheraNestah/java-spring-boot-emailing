package com.SpringBootEmailing.repository;

import com.SpringBootEmailing.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface UserRepository extends JpaRepository<UserEntity, Serializable> {
}
