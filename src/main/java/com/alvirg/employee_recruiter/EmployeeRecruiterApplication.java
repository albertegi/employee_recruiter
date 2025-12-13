package com.alvirg.employee_recruiter;

import com.alvirg.employee_recruiter.role.Role;
import com.alvirg.employee_recruiter.role.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class EmployeeRecruiterApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeRecruiterApplication.class, args);
	}

		@Bean
	public CommandLineRunner runner(RoleRepository roleRepository){
		return args -> {
			final Optional<Role> userRole = roleRepository.findByName("USER_ROLE");
			if(userRole.isEmpty()){
				final Role role = new Role();
				role.setName("ROLE_USER");
				role.setCreatedBy("APP");
				roleRepository.save(role);
			}
		};
	}

}
