package com.example.hosp12;

import com.example.hosp12.entities.Patient;
import com.example.hosp12.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class Hosp12Application implements CommandLineRunner {
private final PatientRepository patientRepository;

	public Hosp12Application(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(Hosp12Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		patientRepository.save(new Patient(null,"mohamed",new Date(),false,34));
		patientRepository.save(new Patient(null,"hanane",new Date(),false,21));
		patientRepository.save(new Patient(null,"imane",new Date(),true,34));

	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
