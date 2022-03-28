package com.th;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.th.dao.TaskRepository;
import com.th.entities.Task;
import com.th.service.AccountServiceImplement;

@SpringBootApplication
public class SimpleAuthApplication implements CommandLineRunner {

	@Autowired
	TaskRepository taskRepository;
	@Autowired
	AccountServiceImplement accountServiceImplement;
	public static void main(String[] args) {
		SpringApplication.run(SimpleAuthApplication.class, args);
	}
	
	@Bean
	// avoir un BCryptEncoder qui est initialisé une et une seule fois
	public BCryptPasswordEncoder getBCPE()
	{
		return new BCryptPasswordEncoder();
		
	}

	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		
		
		Stream.of("T1","T2","T3").forEach(t->{
			taskRepository.save(new Task(t));
			});
			taskRepository.findAll().forEach(t->{
			System.out.println(t.getTaskName());
			});
	}

}
