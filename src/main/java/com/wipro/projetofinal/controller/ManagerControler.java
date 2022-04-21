package com.wipro.projetofinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wipro.projetofinal.entities.Account;
import com.wipro.projetofinal.entities.CheckingAccount;
import com.wipro.projetofinal.entities.Customer;
import com.wipro.projetofinal.entities.Manager;
import com.wipro.projetofinal.entities.SpecialAccount;
import com.wipro.projetofinal.repository.CheckingAccountRepository;
import com.wipro.projetofinal.repository.SpecialAccountRepository;
import com.wipro.projetofinal.service.CustomerService;
import com.wipro.projetofinal.service.ManageService;

import java.util.List;

@RestController
@RequestMapping(value = "/managers")
@CrossOrigin("*")
public class ManagerControler {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ManageService manageService;
	
	@PostMapping
	public ResponseEntity<Manager> salvarManager(@RequestBody Manager manager) {
		return ResponseEntity.status(HttpStatus.CREATED).body(manageService.saveManager(manager));
	}

	@GetMapping("/checkingAccount/{registration}/{accountNumber}")
	public ResponseEntity<CheckingAccount> getByAccountNumberChecking(@PathVariable String registration,@PathVariable String accountNumber){
		CheckingAccount obj = manageService.findByAccountNumberChecking(registration,accountNumber);
		return ResponseEntity.ok().body(obj);
	}
	@GetMapping("/specialAccount/{registration}/{accountNumber}")
	public ResponseEntity<SpecialAccount> getByAccountNumberSpecial(@PathVariable String registration,@PathVariable String accountNumber){
		SpecialAccount obj = manageService.findByAccountNumberSpecial(registration,accountNumber);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping("/{registration}/all")
	public ResponseEntity<List<Account>> getAllAccounts(@PathVariable String registration){
		List<Account> accounts = manageService.findAllAccounts(registration);
		return ResponseEntity.ok().body(accounts);
	}

	@GetMapping("/checkingAccount/{registration}")
	public ResponseEntity<List<CheckingAccount>> getAllChecking(@PathVariable String registration) {
		List<CheckingAccount> list = manageService.findAllChecking(registration);
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/specialAccount/{registration}")
	public ResponseEntity<List<SpecialAccount>> getAllSpecial(@PathVariable String registration){
		List<SpecialAccount> list = manageService.findAllSpecial(registration);
		return ResponseEntity.ok().body(list);
	}

	@PostMapping("/checkingAccount/{registration}") // Feito, so passar para o de baixo
	public ResponseEntity<CheckingAccount> salvarCheckingAccount(@PathVariable String registration, @RequestBody CheckingAccount account) {
		return ResponseEntity.status(HttpStatus.CREATED).body(manageService.saveCheckingAccount(registration,account));
	}

	@PostMapping("/specialAccount/{registration}")
	public ResponseEntity<SpecialAccount> salvarSpecialAccount(@PathVariable String registration, @RequestBody SpecialAccount account) {
		return ResponseEntity.status(HttpStatus.CREATED).body(manageService.saveSpecialAccount(registration,account));
	}

	@DeleteMapping("/checkingAccount/{registration}/{accountNumber}")
	public ResponseEntity<Void> deleteChecking(@PathVariable String registration ,@PathVariable String accountNumber) {
		manageService.deleteAccountChecking(registration,accountNumber);
		return ResponseEntity.noContent().build();
	}
	@DeleteMapping("/specialAccount/{registration}/{accountNumber}")
	public ResponseEntity<Void> deleteSpecial(@PathVariable String registration ,@PathVariable String accountNumber) {
		manageService.deleteAccountSpecial(registration,accountNumber);
		return ResponseEntity.noContent().build();
	}
	
}