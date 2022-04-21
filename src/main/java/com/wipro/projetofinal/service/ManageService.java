package com.wipro.projetofinal.service;

import com.wipro.projetofinal.service.exeption.ResourceNotFoundExcception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.projetofinal.entities.Account;
import com.wipro.projetofinal.entities.CheckingAccount;
import com.wipro.projetofinal.entities.Customer;
import com.wipro.projetofinal.entities.Manager;
import com.wipro.projetofinal.entities.SpecialAccount;
import com.wipro.projetofinal.repository.CheckingAccountRepository;
import com.wipro.projetofinal.repository.CustomerRepository;
import com.wipro.projetofinal.repository.ManagerRepository;
import com.wipro.projetofinal.repository.SpecialAccountRepository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ManageService {
	
	@Autowired
	private ManagerRepository managerRepository;
	
	@Autowired
	private CheckingAccountRepository checkingAccountRepository;
	
	@Autowired
	private SpecialAccountRepository specialAccountRepository;
	
	@Autowired
	private CustomerRepository customerRepository;

	public Manager saveManager(Manager manager) {
		return managerRepository.save(manager);
	}

 	public CheckingAccount saveCheckingAccount(String registration, CheckingAccount account) {
		Manager manager = managerRepository.findByRegistration(registration);
		if(managerRepository.existsById(manager.getId()) == true) {
			account.setCreatedDate(Instant.now());
			account.setAccountNumber();
			return checkingAccountRepository.save(account);    //Se existir esse registro eu consigo salvar o "CheckingAccount".
		};
		return checkingAccountRepository.getById((long) 0);    // Se não existir simplesmente vai dar erro 500 na aplicação.   
	}

	public SpecialAccount saveSpecialAccount(String registration, SpecialAccount account) {
		Manager manager = managerRepository.findByRegistration(registration);
		if(managerRepository.existsById(manager.getId()) == true) {
			account.setAccountNumber();
			account.setCreatedDate(Instant.now());
			return specialAccountRepository.save(account);    //Se existir esse registro eu consigo salvar o "CheckingAccount".
		};
		return specialAccountRepository.getById((long) 0);    // Se não existir simplesmente vai dar erro 500 na aplicação.   
	}

	// ele retorna uma lista vazia nao precisa de execao
	public List<Account> findAllAccounts(String registration) {
		Manager manager = managerRepository.findByRegistration(registration);
		List<Account> accounts = new ArrayList<Account>();
		if(managerRepository.existsById(manager.getId()) == true) {
			accounts.addAll(findAllChecking(registration));
			accounts.addAll(findAllSpecial(registration));
			return accounts;
		}
		return null;
	}

	public List<CheckingAccount> findAllChecking(String registration){

		Manager manager = managerRepository.findByRegistration(registration);
		if(managerRepository.existsById(manager.getId()) == true) {
			return checkingAccountRepository.findAll();
		}
		return null;
	}
	// ele retorna uma lista vazia nao precisa de execao
	public List<SpecialAccount> findAllSpecial(String registration){
		Manager manager = managerRepository.findByRegistration(registration);
		if(managerRepository.existsById(manager.getId())==true){
			return  specialAccountRepository.findAll();
		}
		return null;
	}

	// consigo tratar somente um erro
	public CheckingAccount findByAccountNumberChecking(String registration, String number){
		Manager manager = managerRepository.findByRegistration(registration);
		if(managerRepository.existsById(manager.getId())==true){
			CheckingAccount obj =  checkingAccountRepository.findByAccountNumber(number);
			Optional<CheckingAccount> checking = Optional.ofNullable(obj);
			return checking.orElseThrow(()-> new ResourceNotFoundExcception(number));
		}
		return null;
	}

	// consigo tratar somente um erro
	public SpecialAccount findByAccountNumberSpecial(String registration, String number){
		Manager manager = managerRepository.findByRegistration(registration);
		if(managerRepository.existsById(manager.getId())==true){
			SpecialAccount obj =  specialAccountRepository.findByAccountNumber(number);
			Optional<SpecialAccount> checking = Optional.ofNullable(obj);
			return checking.orElseThrow(()-> new ResourceNotFoundExcception(number));
		}
		return null;
	}

	
	public void deleteAccountChecking(String registration, String number){
		Manager manager = managerRepository.findByRegistration(registration);
		if(managerRepository.existsById(manager.getId())==true){
		 CheckingAccount obj =	checkingAccountRepository.findByAccountNumber(number);
		 checkingAccountRepository.delete(obj);
		}
	}

	public void deleteAccountSpecial(String registration, String number){
		Manager manager = managerRepository.findByRegistration(registration);
		if(managerRepository.existsById(manager.getId())==true){
			SpecialAccount obj = specialAccountRepository.findByAccountNumber(number);
			specialAccountRepository.delete(obj);
		}
	}

	public CheckingAccount updateByAccountNumberChecking(String registration, String number, CheckingAccount updateCa) {
		Manager manager = managerRepository.findByRegistration(registration);
		if(managerRepository.existsById(manager.getId())==true){
			CheckingAccount cadb = checkingAccountRepository.findByAccountNumber(number);
			cadb.setCustomer((Customer)updateCa.getCustomer());
			cadb.setCreditCard(updateCa.getCreditCard());
			return  cadb;
		}
		return null;
	}
	
	public SpecialAccount updateByAccountNumberSpecial(String registration, String number, SpecialAccount updateSa) {
		Manager manager = managerRepository.findByRegistration(registration);
		if(managerRepository.existsById(manager.getId())==true){
			SpecialAccount cadb = specialAccountRepository.findByAccountNumber(number);
			cadb.setCustomer((Customer)updateSa.getCustomer());
			cadb.setCreditCard(updateSa.getCreditCard());
			cadb.setSpecialLimit(updateSa.getSpecialLimit());
			return  cadb;
		}
		return null;
	}
	

}
