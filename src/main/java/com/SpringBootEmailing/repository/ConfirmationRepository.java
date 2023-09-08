package com.SpringBootEmailing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface ConfirmationRepository extends JpaRepository<ConfirmationRepository, Serializable> {
}
