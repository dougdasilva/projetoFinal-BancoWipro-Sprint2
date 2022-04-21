package com.wipro.projetofinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.projetofinal.entities.Customer;
import com.wipro.projetofinal.service.CustomerService;

@RestController
@RequestMapping(value = "/customers")
@CrossOrigin("*")
public class CustomerControler {

	@Autowired
	private CustomerService customerService;
	
	
}

