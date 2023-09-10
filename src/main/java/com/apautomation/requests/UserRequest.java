package com.apautomation.requests;

import java.util.ArrayList;
import java.util.Set;

import jakarta.validation.constraints.*;

public class UserRequest {
  @NotBlank
  @Size(min = 3, max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  private Set<String> role;

  @NotBlank
  @Size(min = 6, max = 120)
  private String password;
  
  @NotBlank
  @Size(max = 50)
  private String name;
  
  
  private ArrayList<String> dept;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<String> getRole() {
    return this.role;
  }

  public void setRole(Set<String> role) {
    this.role = role;
  }

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<String> getDept() {
		return dept;
	}
	
	public void setDept( ArrayList<String> dept) {
		this.dept = dept;
	}
}