package com.apautomation.modules.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.apautomation.modules.employee.model.Department;
import com.apautomation.modules.employee.model.Type;
import com.apautomation.modules.employee.repository.TypeRepository;
import com.apautomation.responses.MessageResponse;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/common")
public class TypeController {
	
	@Autowired
	private TypeRepository typeRepository;
	
	@PostMapping("/type")
	ResponseEntity<MessageResponse> newType(@RequestBody Type newType) {
		if (typeRepository.existsById(newType.getTypeId())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: GL Code already taken"));
		}else {
			typeRepository.save(newType);
		}
		return ResponseEntity.ok(new MessageResponse("Type registered successfully!"));
	}
	
	@GetMapping("/types")
	List<Type> getAllTypes(){
		return typeRepository.findAll();
	}
	
	@GetMapping("/type/{id}")
	Type getTypeById(@PathVariable Long id) {
		return typeRepository.findById(id)
				.orElseThrow(()->new NotFoundException(id, "type"));
	}
	
	@PutMapping("/type/{id}")
	Type updateType(@RequestBody Type newType, @PathVariable Long id) {
		return typeRepository.findById(id)
				.map(type -> {
					type.setTypeName(newType.getTypeName());
					type.setTypeName(newType.getTypeName());
					
					return typeRepository.save(type);
				}).orElseThrow(() -> new NotFoundException(id, "type"));
	}
	
	@DeleteMapping("/type/{id}")
	String deleteType(@PathVariable Long id) {
		if(!typeRepository.existsById(id)) {
			throw new NotFoundException(id, "type");
		}
		typeRepository.deleteById(id);
		return "Type with id "+id+" has been deleted.";
	}
}
