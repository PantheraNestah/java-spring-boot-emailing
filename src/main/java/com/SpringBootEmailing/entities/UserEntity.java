package com.SpringBootEmailing.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "users_table")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100)
    private String name;
    @Column(length = 100, unique = true, nullable = false)
    private String email;
    @Column(nullable = false, columnDefinition = "default bit 0")
    private Boolean isEnabled;
}
