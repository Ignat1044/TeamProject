package com.example.SDPK.repository;

import com.example.SDPK.model.DiplomaProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiplomaProjectRepository extends JpaRepository<DiplomaProject, Long> {
    // Додаткові методи, якщо необхідно
}
