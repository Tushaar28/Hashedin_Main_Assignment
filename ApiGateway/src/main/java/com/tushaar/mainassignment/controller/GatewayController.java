package com.tushaar.mainassignment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class GatewayController {
	
	@GetMapping("/users")
	public String userServiceFallbackMessage() {
		return "There is some error in connecting to User Service. Please try again later";
	}

}
