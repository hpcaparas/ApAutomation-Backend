package com.apautomation.modules.login.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apautomation.config.JWTAuthenticationFilter;
import com.apautomation.config.JwtUtils;
import com.apautomation.modules.employee.model.ERole;
import com.apautomation.modules.employee.model.Role;
import com.apautomation.modules.employee.model.User;
import com.apautomation.modules.employee.repository.RoleRepository;
import com.apautomation.modules.employee.repository.UserRepository;
import com.apautomation.requests.LoginRequest;
import com.apautomation.requests.UserRequest;
import com.apautomation.responses.JwtResponse;
import com.apautomation.responses.MessageResponse;
import com.apautomation.services.UserDetailsImpl;

import jakarta.validation.Valid;

//@CrossOrigin(origins = "*", maxAge = 3600)
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
	
	private static final Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter.class);
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
    
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		logger.info(userDetails.getUsername());

		return ResponseEntity.ok(new JwtResponse(jwt, 
                         userDetails.getId(),  
                         userDetails.getUsername(), 
                         userDetails.getEmail(), 
                         userDetails.getName(),
                         userDetails.getDept(),
                         roles));
	}

	/*
	 * @PostMapping("/signup") public ResponseEntity<?>
	 * registerUser(@Valid @RequestBody SignupRequest signUpRequest) { if
	 * (userRepository.existsByUsername(signUpRequest.getUsername())) { return
	 * ResponseEntity .badRequest() .body(new
	 * MessageResponse("Error: Username is already taken!")); }
	 * 
	 * if (userRepository.existsByEmail(signUpRequest.getEmail())) { return
	 * ResponseEntity .badRequest() .body(new
	 * MessageResponse("Error: Email is already in use!")); }
	 * 
	 * // Create new user's account User user = new
	 * User(signUpRequest.getUsername(), signUpRequest.getEmail(),
	 * encoder.encode(signUpRequest.getPassword()), signUpRequest.getName(),
	 * signUpRequest.getDept());
	 * 
	 * Set<String> strRoles = signUpRequest.getRole(); Set<Role> roles = new
	 * HashSet<>();
	 * 
	 * if (strRoles == null) { Role userRole =
	 * roleRepository.findByName(ERole.ROLE_USER) .orElseThrow(() -> new
	 * RuntimeException("Error: Role is not found.")); roles.add(userRole); } else {
	 * strRoles.forEach(role -> { switch (role) { case "admin": Role adminRole =
	 * roleRepository.findByName(ERole.ROLE_ADMIN) .orElseThrow(() -> new
	 * RuntimeException("Error: Role is not found.")); roles.add(adminRole);
	 * 
	 * break; case "mod": Role modRole =
	 * roleRepository.findByName(ERole.ROLE_MODERATOR) .orElseThrow(() -> new
	 * RuntimeException("Error: Role is not found.")); roles.add(modRole);
	 * 
	 * break; default: Role userRole = roleRepository.findByName(ERole.ROLE_USER)
	 * .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	 * roles.add(userRole); } }); }
	 * 
	 * user.setRoles(roles); userRepository.save(user);
	 * 
	 * return ResponseEntity.ok(new
	 * MessageResponse("User registered successfully!")); }
	 */
}