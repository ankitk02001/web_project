package com.webapp.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.dto.ReadRegistrationDto;
import com.webapp.dto.RegistrationDto;
import com.webapp.entity.Registration;
import com.webapp.exception.ResourceNotFoundException;
import com.webapp.repository.RegistrationRepository;

@RestController//to make API layer of project
@RequestMapping("/api/registrations")//to call the class
public class RestRegistratonController {
   
	@Autowired
	private RegistrationRepository registrationRepository;
	
	//http://localhost:8080/api/registrations
	@GetMapping
	public ResponseEntity<ReadRegistrationDto> getAllReg(){
		List<Registration> registrations = registrationRepository.findAll();
		ReadRegistrationDto dto=new ReadRegistrationDto();
		dto.setRegistration(registrations);
		dto.setMessage("Reading Completed!!");
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	//http://localhost:8080/api/registrations/6
	@DeleteMapping("/{id}") 
	public ResponseEntity<?> deleteRegistration(@PathVariable long id) {
		Optional<Registration> findById = registrationRepository.findById(id);
		if(findById.isPresent()) {
			registrationRepository.deleteById(id);
		}else {
			throw new ResourceNotFoundException("Registration Not found With Id:"+id);
		}
		
		registrationRepository.deleteById(id);
		return new ResponseEntity<>("Record is deleted!!", HttpStatus.OK);
	}
	
	//save Registration
	//http://localhost:8080/api/registrations
	@PostMapping
	public ResponseEntity<?> saveRegistration(@Valid @RequestBody Registration reg,
			BindingResult result
			) {
		
		if(result.hasErrors()) {
			return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		Registration savedReg = registrationRepository.save(reg);//Entity Class 
		RegistrationDto dto=new RegistrationDto();
		dto.setFirstName(savedReg.getFirstName());
		dto.setEmail(savedReg.getEmail());
		dto.setMobile(savedReg.getMobile());
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}
	
	
	//updating 4 no record
	//http://localhost:8080/api/registrations?id=4
//	@PutMapping
//	public ResponseEntity<Registration> updateRegistration(@RequestParam long id,@RequestBody RegistrationDto registrationDto) {
//		
//		Registration registration = registrationRepository.findById(id).get();
//		registration.setFirstName(registrationDto.getFirstName());
//		registration.setLastName(registrationDto.getLastName());
//		registration.setEmail(registrationDto.getEmail());
//		registration.setMobile(registrationDto.getMobile());
//		
//		Registration updatedReg = registrationRepository.save(registration);
//		return new ResponseEntity<>(updatedReg, HttpStatus.OK);
//	}
	
}
