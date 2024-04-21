package com.example.hosp12.security.repos;

import com.example.hosp12.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,String > {
}