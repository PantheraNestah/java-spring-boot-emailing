package com.SpringBootEmailing.repository;

import com.SpringBootEmailing.entities.ConfirmationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface ConfirmationRepository extends JpaRepository<ConfirmationEntity, Serializable> {
    ConfirmationEntity findByToken(String token);
    ConfirmationEntity save(ConfirmationEntity confirmation);
}
