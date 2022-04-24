package com.wipro.projetofinal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.projetofinal.entities.Account;
import com.wipro.projetofinal.entities.Moviment;
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

	@GetMapping("/movimenties/{accountNumber}")
	public ResponseEntity<List<Moviment>> getMovimentAccount(@PathVariable String accountNumber) {
		return ResponseEntity.ok().body(customerService.getAllMovimenties(accountNumber));
	}

	@PutMapping("/deposit/{accountNumber}")
	public ResponseEntity<Account> depositAccount(@PathVariable String accountNumber, @RequestParam Double value)
			throws Exception {
		return ResponseEntity.ok().body(customerService.deposit(accountNumber, value));
	}

	@PutMapping("/withdraw/{accountNumber}")
	public ResponseEntity<Account> withdrawAccount(@PathVariable String accountNumber, @RequestParam Double value)
			throws Exception {

		return ResponseEntity.ok().body(customerService.withdraw(accountNumber, value));
	}

	@PutMapping("/transfer/{originAccount}/{destinationAccount}")
	public ResponseEntity<Account> transferAccount(@PathVariable String originAccount,
			@PathVariable String destinationAccount, @RequestParam Double value) throws Exception {
		return ResponseEntity.ok().body(customerService.transfer(originAccount, destinationAccount, value));
	}

}
