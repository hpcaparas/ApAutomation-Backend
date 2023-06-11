package com.apautomation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apautomation.exception.UserNotFoundException;
import com.apautomation.model.Employee;
import com.apautomation.repository.UserRepository;


@RestController
//@CrossOrigin("https://apautomation-backend.azurewebsites.net")
//@CrossOrigin("http://localhost:3000")
@CrossOrigin("http://localhost:3000")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/employee")
	Employee newEmp(@RequestBody Employee newEmp) {
		return userRepository.save(newEmp);
	}
	
	@GetMapping("/employees")
	List<Employee> getAllUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping("/employee/{id}")
	Employee getUserById(@PathVariable Long id) {
		return userRepository.findById(id)
				.orElseThrow(()->new UserNotFoundException(id));
	}
	
	@PutMapping("/employee/{id}")
	Employee updateUser(@RequestBody Employee newUser, @PathVariable Long id) {
		return userRepository.findById(id)
				.map(user -> {
					user.setUsername(newUser.getUsername());
					user.setName(newUser.getName());
					user.setEmail(newUser.getEmail());
					
					return userRepository.save(user);
				}).orElseThrow(() -> new UserNotFoundException(id));
	}
	
	@DeleteMapping("/employee/{id}")
	String deleteUser(@PathVariable Long id) {
		if(!userRepository.existsById(id)) {
			throw new UserNotFoundException(id);
		}
		userRepository.deleteById(id);
		return "User with id "+id+" has been deleted.";
	}
}
