package com.example.SDPK.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DiplomaProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cadetName;
    private String projectTitle;
    private String supervisor;
    private String reviewer;
    private int year;

    // Конструктори, геттери та сеттери
}
