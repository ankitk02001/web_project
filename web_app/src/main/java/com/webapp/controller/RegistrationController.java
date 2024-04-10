package com.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.webapp.dto.RegistrationDto;
import com.webapp.entity.Registration;
import com.webapp.service.RegistrationService;
import com.webapp.util.EmailService;

@Controller // make it controller class
public class RegistrationController {
	
	 @Autowired
	private EmailService emailService;
	
	@Autowired
	private RegistrationService registrationService;
	// Handler Method
	// act like get and post method
	// http://localhost:8080/View-registration-page
	@RequestMapping("/View-registration-page")
	public String viewsRegistrationPage() {
		return "new_registration";// return keyword act as request Dispather
	}

	@RequestMapping("/viewHtml")
	public String viewHtml() {
		return "NewFile.html";
	}

//	@RequestMapping("/saveReg")
//	public String saveRegistration(@ModelAttribute Registration registration) {
////		System.out.println(registration.getFirstName());
////		System.out.println(registration.getLastName());
////		System.out.println(registration.getEmail());
////		System.out.println(registration.getMobile());
//		
//		registrationService.saveRegistration(registration);
//		return "new_registration";
//	}

	// Second way to read datamike@gmail.com
//	@RequestMapping("/saveReg")
//	public String saveRegistration(@RequestParam("firstName") String fName,
//			@RequestParam("lastName") String lName,
//			@RequestParam("email") String email, 
//			@RequestParam("mobile") long mobile,
//			ModelMap model
//			) {
//		Registration registration = new Registration();
//		registration.setFirstName(fName);
//		registration.setLastName(lName);
//		registration.setEmail(email);
//		registration.setMobile(mobile);
//		registrationService.saveRegistration(registration);
//		model.addAttribute("msg", "Record id saved!!");
//		return "new_registration";
//	}
	
	
	//third way to save data
	@RequestMapping("/saveReg")
	public String saveRegistration(@RequestParam("firstName") String fName,
			RegistrationDto dto,
			ModelMap model
			) {
		Registration registration = new Registration();
		registration.setFirstName(dto.getFirstName());
	//	registration.setLastName(dto.getLastName());
		registration.setEmail(dto.getEmail());
		registration.setMobile(dto.getMobile());
		registrationService.saveRegistration(registration);
		emailService.sendEmail(dto.getEmail(), "Welcome", "test");
		model.addAttribute("msg", "Record id saved!!");
		return "new_registration";
	}
	
	@RequestMapping("/getAllReg")
	public String getAllRegistrations(Model model) {
	List<Registration> reg=registrationService.getAllRegistrations();
	model.addAttribute("registrations", reg);
	return "list_registrations";
	}
	
	@RequestMapping("/delete")
	public String deleteRegById(@RequestParam("id") long id, Model model) {
		registrationService.deleteRegById(id);
		List<Registration> reg=registrationService.getAllRegistrations();
		model.addAttribute("registrations", reg);
		return "list_registrations";
}
	
	@RequestMapping("/getRegistrationById")
	public String getRegistrationById(@RequestParam("id") long id, Model model) {
		Registration registration =registrationService.getRegistrationById(id);
		model.addAttribute("reg", registration);
		return "update_registration"; 
	}
//	@RequestMapping("/updateReg")
//	public String updateRegistration(@RequestParam("firstName") String fName,
//			RegistrationDto dto,
//			ModelMap model
//			) {
//		Registration registration = new Registration();
//		registration.setId(dto.getId());
//		registration.setFirstName(dto.getFirstName());
//		registration.setLastName(dto.getLastName());
//		registration.setEmail(dto.getEmail());
//		registration.setMobile(dto.getMobile());
//		registrationService.saveRegistration(registration);
//		List<Registration> reg=registrationService.getAllRegistrations();
//		model.addAttribute("registrations", reg);
//		return "list_registrations";
//
//	}
}