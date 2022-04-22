package com.wipro.projetofinal.service;

import com.wipro.projetofinal.service.exeption.AlreadyExistAccountByCpf;
import com.wipro.projetofinal.service.exeption.ResourceNotFoundExcception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
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
 		
		if(manager != null) {			//Se existir esse registro eu consigo entrar no fluxo abaixo.
			account.setCreatedDate(Instant.now());
			account.setAccountNumber();
			
			String customerCpf = account.getCustomer().getCpf();      // Pego o cpf do Customer mandado pelo JSON
			Customer customer = customerRepository.findByCpf(customerCpf); // Pego o Customer associado ao cpf acima
			
				if(checkingAccountRepository.findByCustomer(customer) == null) {   // Se existir um checkingAccount com esse Customer ñ é pra deixar salvar
					if(customerRepository.findByCpf(customerCpf) == null) {  // Se já existir um Customer com esse cpf, so salva a Account e descartar o customer
					return checkingAccountRepository.save(account);
				} 	else {
					account.setCustomer(customer);
					return checkingAccountRepository.save(account);
				}
				
		} 		else {
				 throw new AlreadyExistAccountByCpf(customerCpf);
			}
				
					
		}else {
			throw new NullPointerException("Valor inexistente"); }   // Se não existir simplesmente vai dar erro 500 na aplicação.   
	}

	public SpecialAccount saveSpecialAccount(String registration, SpecialAccount account) {
		Manager manager = managerRepository.findByRegistration(registration);
		if(manager != null) {
			account.setAccountNumber();
			account.setCreatedDate(Instant.now());
			
			
			String customerCpf = account.getCustomer().getCpf();      // Pego o cpf do Customer mandado pelo JSON
			Customer customer = customerRepository.findByCpf(customerCpf); // Pego o Customer associado ao cpf acima
			if(specialAccountRepository.findByCustomer(customer) == null) {   // Se existir um checkingAccount com esse Customer ñ é pra deixar salvar
				if(customerRepository.findByCpf(customerCpf) == null) {  // Se já existir um Customer com esse cpf, so salva a Account e descartar o customer
					return specialAccountRepository.save(account);
				} else {
					account.setCustomer(customer);
					return specialAccountRepository.save(account);
				}
				
			} else {
				throw new AlreadyExistAccountByCpf(customerCpf);
			}
					
		}else {
			throw new NullPointerException("Valor inexistente"); }
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

	// tratamento feito
	public void deleteAccountChecking(String registration, String number){
		try{
			Manager manager = managerRepository.findByRegistration(registration);
			if(managerRepository.existsById(manager.getId())==true){
				CheckingAccount obj =	checkingAccountRepository.findByAccountNumber(number);
				checkingAccountRepository.delete(obj);
			}
		}
		catch (InvalidDataAccessApiUsageException e){
			throw new ResourceNotFoundExcception(number);
		}
	}
	// tratamento feito
	public void deleteAccountSpecial(String registration, String number){
		try {
			Manager manager = managerRepository.findByRegistration(registration);
			if (managerRepository.existsById(manager.getId()) == true) {
				SpecialAccount obj = specialAccountRepository.findByAccountNumber(number);
				specialAccountRepository.delete(obj);
			}
		}
		catch (InvalidDataAccessApiUsageException e){
			throw new ResourceNotFoundExcception(number);
		}
	}

	public CheckingAccount updateByAccountNumberChecking(String registration, String number, CheckingAccount updateCa) {
		Manager manager = managerRepository.findByRegistration(registration);
		if(managerRepository.existsById(manager.getId())==true){
			CheckingAccount cadb = checkingAccountRepository.findByAccountNumber(number);
			cadb.setCustomer(updateCa.getCustomer());
			cadb.setCreditCard(updateCa.getCreditCard());
			checkingAccountRepository.save(cadb);
			return  cadb;
		}
		return null;
	}

	public SpecialAccount updateByAccountNumberSpecial(String registration, String number, SpecialAccount updateSa) {
		Manager manager = managerRepository.findByRegistration(registration);
		if(managerRepository.existsById(manager.getId())==true){
			SpecialAccount cadb = specialAccountRepository.findByAccountNumber(number);
			cadb.setCustomer(updateSa.getCustomer());
			cadb.setCreditCard(updateSa.getCreditCard());
			cadb.setSpecialLimit(updateSa.getSpecialLimit());
			specialAccountRepository.save(cadb);
			return  cadb;
		}
		return null;
	}


}
