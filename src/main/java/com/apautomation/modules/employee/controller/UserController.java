package com.apautomation.modules.employee.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apautomation.modules.employee.exception.NotFoundException;
import com.apautomation.modules.employee.model.ERole;
import com.apautomation.modules.employee.model.Role;
import com.apautomation.modules.employee.model.User;
import com.apautomation.modules.employee.repository.RoleRepository;
import com.apautomation.modules.employee.repository.UserRepository;
import com.apautomation.requests.UserRequest;
import com.apautomation.responses.MessageResponse;

import jakarta.validation.Valid;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
@CrossOrigin("https://apautomation-backend.azurewebsites.net")
//@CrossOrigin("http://localhost:3000")
public class UserController {

	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	 @Autowired
	 RoleRepository roleRepository;
	
	@PostMapping("/employee")
	//@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	/*
	 * User newEmp(@RequestBody User newEmp) { return userRepository.save(newEmp); }
	 */
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
    // Create new user's account
		String deptStr = "";
		for(int i = 0; i < signUpRequest.getDept().size(); i++) {
			String deptLi = signUpRequest.getDept().get(i);
			if(i == signUpRequest.getDept().size() - 1)
				deptStr = deptStr + deptLi;
			else
				deptStr = deptStr + deptLi + ",";
		}
		
		User user = new User(signUpRequest.getUsername(), 
               signUpRequest.getEmail(),
               encoder.encode(signUpRequest.getPassword()),
               signUpRequest.getName(),
               deptStr);

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "ROLE_ADMIN":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "ROLE_MODERATOR":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				case "ROLE_FINANCE":
					Role finRole = roleRepository.findByName(ERole.ROLE_FINANCE)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(finRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	@GetMapping("/employees")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN') or hasRole('FINANCE')")
	List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping("/employee/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN') or hasRole('FINANCE')")
	User getUserById(@PathVariable Long id) {
		return userRepository.findById(id)
				.orElseThrow(()->new NotFoundException(id, "user"));
	}
	
	@PutMapping("/employee/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN') or hasRole('FINANCE')")
	User updateUser(@Valid @RequestBody UserRequest userRequest, @PathVariable Long id) {
		
		
		return userRepository.findById(id)
				.map(user -> {
					String deptStr = "";
					for(int i = 0; i < userRequest.getDept().size(); i++) {
						String deptLi = userRequest.getDept().get(i);
						if(i == userRequest.getDept().size() - 1)
							deptStr = deptStr + deptLi;
						else
							deptStr = deptStr + deptLi + ",";
					}
					
					Set<String> strRoles = userRequest.getRole();
					Set<Role> roles = new HashSet<>();

					if (strRoles == null) {
						Role userRole = roleRepository.findByName(ERole.ROLE_USER)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(userRole);
					} else {
						strRoles.forEach(role -> {
							switch (role) {
							case "admin":
								Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
								roles.add(adminRole);

								break;
							case "mod":
								Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
								roles.add(modRole);

								break;
							default:
								Role userRole = roleRepository.findByName(ERole.ROLE_USER)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
								roles.add(userRole);
							}
						});
					}
					
					user.setUsername(userRequest.getUsername());
					user.setName(userRequest.getName());
					user.setEmail(userRequest.getEmail());
					user.setDept(deptStr);
					user.setPassword(userRequest.getPassword());
					user.setRoles(roles);
					
					return userRepository.save(user);
				}).orElseThrow(() -> new NotFoundException(id, "user"));
	}
	
	@PutMapping("/resetPass/employee/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN') or hasRole('FINANCE')")
	User updateUserPass(@RequestBody User newUser, @PathVariable Long id) {
		return userRepository.findById(id)
				.map(user -> {
					user.setPassword(newUser.getPassword());
					
					return userRepository.save(user);
				}).orElseThrow(() -> new NotFoundException(id, "user"));
	}
	
	@DeleteMapping("/employee/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN') or hasRole('FINANCE')")
	String deleteUser(@PathVariable Long id) {
		if(!userRepository.existsById(id)) {
			throw new NotFoundException(id, "user");
		}
		userRepository.deleteById(id);
		return "User with id "+id+" has been deleted.";
	}
}
