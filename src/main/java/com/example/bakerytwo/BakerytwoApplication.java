package com.example.bakerytwo;

import com.example.bakerytwo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BakerytwoApplication {

	@Autowired
	private BreadRepository breadRepository;

	@Autowired
	private TypeRepository typeRepository;

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(BakerytwoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(){
		return args -> {
			// Add Type objects and save to db
			Type t1 = new Type("Ruis");
			Type t2 = new Type("Kaura");
			Type t3 = new Type("Vehna");
			Type t4 = new Type("Gluten-Free");
			typeRepository.save(t1);
			typeRepository.save(t2);
			typeRepository.save(t3);
			typeRepository.save(t4);
			//add bread objects
			Bread b1 = new Bread("Ruismies","Fazer",0.45, 5,t1);
			Bread b2 = new Bread("Kauratyynyt", "Vaasan", 1.39, 4, t2);
			//save breads to breadRepository
			breadRepository.save(b1);
			breadRepository.save(b2);


			//username: user password: user
			userRepository.deleteAll();
			userRepository.save(new User("user", "$2a$04$1.YhMIgNX/8TkCKGFUONWO1waedKhQ5KrnB30fl0Q01QKqmzLf.Zi", "USER"));
			//username:adimin, password:admin
			userRepository.save(new User("admin", "$2a$04$KNLUwOWHVQZVpXyMBNc7JOzbLiBjb9Tk9bP7KNcPI12ICuvzXQQKG","ADMIN"));


		};
	}
}
