package com.wipro.projetofinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.projetofinal.entities.Account;
import com.wipro.projetofinal.entities.Customer;
import com.wipro.projetofinal.service.CustomerService;

@RestController
@RequestMapping(value = "/customers")
@CrossOrigin("*")
public class CustomerControler {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/{accountNumber}")
	public ResponseEntity<Account> getAccount(@PathVariable String accountNumber) {
		return ResponseEntity.ok().body(customerService.getAccount(accountNumber));
	}
	
	@PutMapping("/{accountNumber}")
	public ResponseEntity<Account> depositAccount(@PathVariable String accountNumber, @RequestParam Double value ){
		return ResponseEntity.ok().body(customerService.deposit(accountNumber, value));
	}
	
}

