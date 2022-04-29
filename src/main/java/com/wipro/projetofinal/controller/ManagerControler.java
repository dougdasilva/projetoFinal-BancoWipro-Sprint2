package com.wipro.projetofinal.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.projetofinal.dto.AccountChekingDTO;
import com.wipro.projetofinal.dto.AccountSpecialDTO;
import com.wipro.projetofinal.entities.Account;
import com.wipro.projetofinal.entities.CheckingAccount;
import com.wipro.projetofinal.entities.CreditCard;
import com.wipro.projetofinal.entities.Customer;
import com.wipro.projetofinal.entities.Manager;
import com.wipro.projetofinal.entities.SpecialAccount;
import com.wipro.projetofinal.service.ManageService;

@RestController
@RequestMapping(value = "/managers")
@CrossOrigin("*")
public class ManagerControler {

	@Autowired
	private ManageService manageService;
	
//	@Api()
	@PostMapping
	public ResponseEntity<Manager> saveManager(@RequestBody Manager manager)
			throws Exception, SQLIntegrityConstraintViolationException {
		return ResponseEntity.status(HttpStatus.CREATED).body(manageService.saveManager(manager));
	}
	
	@GetMapping("/validationPassword")
	public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password ){
		String obj = manageService.login(email,password);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping("/checkingAccount/{registration}/{accountNumber}")
	public ResponseEntity<CheckingAccount> getByAccountNumberChecking(@PathVariable String registration,
			@PathVariable String accountNumber) {
		CheckingAccount obj = manageService.findByAccountNumberChecking(registration, accountNumber);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping("/specialAccount/{registration}/{accountNumber}")
	public ResponseEntity<SpecialAccount> getByAccountNumberSpecial(@PathVariable String registration,
			@PathVariable String accountNumber) {
		SpecialAccount obj = manageService.findByAccountNumberSpecial(registration, accountNumber);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping("/accountAll/{registration}")
	public ResponseEntity<List<Account>> getAllAccounts(@PathVariable String registration) {
		List<Account> accounts = manageService.findAllAccounts(registration);
		return ResponseEntity.ok().body(accounts);
	}

	@GetMapping("/checkingAccount/{registration}")
	public ResponseEntity<List<CheckingAccount>> getAllChecking(@PathVariable String registration) {
		List<CheckingAccount> list = manageService.findAllChecking(registration);
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/specialAccount/{registration}")
	public ResponseEntity<List<SpecialAccount>> getAllSpecial(@PathVariable String registration) throws Exception {
		List<SpecialAccount> list = manageService.findAllSpecial(registration);
		return ResponseEntity.ok().body(list);
	}

	@PostMapping("/checkingAccount/{registration}") // Feito, so passar para o de baixo
	public ResponseEntity<AccountChekingDTO> salvarCheckingAccount(@PathVariable String registration,
			@RequestBody CheckingAccount account, BindingResult result) throws Exception {
		return ResponseEntity.status(HttpStatus.CREATED).body(manageService.saveCheckingAccount(registration, account));
	}

	@PostMapping("/specialAccount/{registration}")
	public ResponseEntity<AccountSpecialDTO> salvarSpecialAccount(@PathVariable String registration,
			@RequestBody SpecialAccount account) throws Exception {
		return ResponseEntity.status(HttpStatus.CREATED).body(manageService.saveSpecialAccount(registration, account));
	}

	@PutMapping("/customer/{registration}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable String registration,
			@RequestBody @Valid Customer customerUpdate) throws Exception {
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(manageService.updateCustomer(registration, customerUpdate));
	}

	@PutMapping("/activateCard/{registration}/{accountNumber}")
	public ResponseEntity<Account> activateCard(@PathVariable String registration, @PathVariable String accountNumber,
			@RequestBody CreditCard creditCard) {
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(manageService.createNewCreditCard(registration, accountNumber, creditCard));
	}

	@PutMapping("/updateCard/{registration}/{accountNumber}")
	public ResponseEntity<Account> updateCard(@PathVariable String registration, @PathVariable String accountNumber,
			@RequestBody CreditCard creditCard) {
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(manageService.updateCreditCardLimit(registration, accountNumber, creditCard));
	}

	@DeleteMapping("/checkingAccount/{registration}/{accountNumber}")
	public ResponseEntity<Void> deleteChecking(@PathVariable String registration, @PathVariable String accountNumber)
			throws Exception {
		manageService.deleteAccountChecking(registration, accountNumber);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/specialAccount/{registration}/{accountNumber}")
	public ResponseEntity<Void> deleteSpecial(@PathVariable String registration, @PathVariable String accountNumber)
			throws Exception {
		manageService.deleteAccountSpecial(registration, accountNumber);
		return ResponseEntity.noContent().build();
	}

}