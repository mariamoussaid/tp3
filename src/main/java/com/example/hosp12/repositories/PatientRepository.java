package com.example.hosp12.repositories;

import com.example.hosp12.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findByNomContains(String keyword, Pageable pageable);
    @Query("SELECT p FROM Patient p WHERE p.nom like :x")
    Page<Patient> chercher(@Param("x") String keyword, Pageable pageable);
}