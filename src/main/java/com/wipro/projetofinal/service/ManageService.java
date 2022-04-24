package com.wipro.projetofinal.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import com.wipro.projetofinal.dto.AccountChekingDTO;
import com.wipro.projetofinal.dto.AccountSpecialDTO;
import com.wipro.projetofinal.entities.Account;
import com.wipro.projetofinal.entities.CheckingAccount;
import com.wipro.projetofinal.entities.CreditCard;
import com.wipro.projetofinal.entities.Customer;
import com.wipro.projetofinal.entities.Manager;
import com.wipro.projetofinal.entities.SpecialAccount;
import com.wipro.projetofinal.repository.CheckingAccountRepository;
import com.wipro.projetofinal.repository.CustomerRepository;
import com.wipro.projetofinal.repository.ManagerRepository;
import com.wipro.projetofinal.repository.SpecialAccountRepository;
import com.wipro.projetofinal.service.exeption.AlreadExistException;
import com.wipro.projetofinal.service.exeption.AlreadyExistAccountByCpf;
import com.wipro.projetofinal.service.exeption.ResourceNotFoundExcception;

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

	public AccountChekingDTO saveCheckingAccount(String registration, CheckingAccount account) {

		Manager manager = managerRepository.findByRegistration(registration);

		if (manager != null) { // Se existir esse registro eu consigo entrar no fluxo abaixo.
			account.setCreatedDate(Instant.now());
			account.setAccountNumber();

			String customerCpf = account.getCustomer().getCpf(); // Pego o cpf do Customer mandado pelo JSON
			Customer customer = customerRepository.findByCpf(customerCpf); // Pego o Customer associado ao cpf acima

			if (checkingAccountRepository.findByCustomer(customer) == null) { // Se existir um checkingAccount com esse
																				// Customer ñ é pra deixar salvar
				if (customerRepository.findByCpf(customerCpf) == null) { // Se já existir um Customer com esse cpf, so
																			// salva a Account e descartar o customer
					CheckingAccount checkingAccount = checkingAccountRepository.save(account);
					AccountChekingDTO accountDTO = new AccountChekingDTO(checkingAccount);
					return accountDTO;
				} else {
					account.setCustomer(customer);
					CheckingAccount checkingAccount = checkingAccountRepository.save(account);
					AccountChekingDTO accountDTO = new AccountChekingDTO(checkingAccount);
					return accountDTO;
				}

			} else {
				throw new AlreadyExistAccountByCpf(customerCpf);
			}

		} else {
			throw new NullPointerException("Matrícula de Gerente inexistente");
		}
	}

	public AccountSpecialDTO saveSpecialAccount(String registration, SpecialAccount account) {
		Manager manager = managerRepository.findByRegistration(registration);
		if (manager != null) {
			account.setAccountNumber();
			account.setCreatedDate(Instant.now());

			String customerCpf = account.getCustomer().getCpf(); // Pego o cpf do Customer mandado pelo JSON
			Customer customer = customerRepository.findByCpf(customerCpf); // Pego o Customer associado ao cpf acima
			if (specialAccountRepository.findByCustomer(customer) == null) { // Se existir um checkingAccount com esse
																				// Customer ñ é pra deixar salvar
				if (customerRepository.findByCpf(customerCpf) == null) { // Se já existir um Customer com esse cpf, so
																			// salva a Account e descartar o customer
					SpecialAccount specialAccount = specialAccountRepository.save(account);
					AccountSpecialDTO accountDTO = new AccountSpecialDTO(specialAccount);
					return accountDTO;
				} else {
					account.setCustomer(customer);
					SpecialAccount specialAccount = specialAccountRepository.save(account);
					AccountSpecialDTO accountDTO = new AccountSpecialDTO(specialAccount);
					return accountDTO;
				}

			} else {
				throw new AlreadyExistAccountByCpf(customerCpf);
			}

		} else {
			throw new NullPointerException("Matrícula de Gerente inexistente");
		}
	}

	// ele retorna uma lista vazia nao precisa de execao
	public List<Account> findAllAccounts(String registration) {
		Manager manager = managerRepository.findByRegistration(registration);
		List<Account> accounts = new ArrayList<Account>();
		if (manager != null) {
			accounts.addAll(findAllChecking(registration));
			accounts.addAll(findAllSpecial(registration));
			return accounts;
		} else {
			throw new NullPointerException("Matrícula de Gerente inexistente");
		}
	}

	public List<CheckingAccount> findAllChecking(String registration) {

		Manager manager = managerRepository.findByRegistration(registration);
		if (manager != null) {
			return checkingAccountRepository.findAll();
		} else {
			throw new NullPointerException("Matrícula de Gerente inexistente");
		}
	}

	// ele retorna uma lista vazia nao precisa de execao
	public List<SpecialAccount> findAllSpecial(String registration) {
		Manager manager = managerRepository.findByRegistration(registration);
		if (manager != null) {
			return specialAccountRepository.findAll();
		} else {
			throw new NullPointerException("Matrícula de Gerente inexistente");
		}
	}

	// consigo tratar somente um erro
	public CheckingAccount findByAccountNumberChecking(String registration, String number) {
		Manager manager = managerRepository.findByRegistration(registration);
		if (manager != null) {
			CheckingAccount obj = checkingAccountRepository.findByAccountNumber(number);
			Optional<CheckingAccount> checking = Optional.ofNullable(obj);
			return checking.orElseThrow(() -> new ResourceNotFoundExcception(number));
		} else {
			throw new NullPointerException("Matrícula de Gerente inexistente");
		}
	}

	// consigo tratar somente um erro
	public SpecialAccount findByAccountNumberSpecial(String registration, String number) {
		Manager manager = managerRepository.findByRegistration(registration);
		if (manager != null) {
			SpecialAccount obj = specialAccountRepository.findByAccountNumber(number);
			Optional<SpecialAccount> checking = Optional.ofNullable(obj);
			return checking.orElseThrow(() -> new ResourceNotFoundExcception(number));
		} else {
			throw new NullPointerException("Matrícula de Gerente inexistente");
		}
	}

	// tratamento feito
	public void deleteAccountChecking(String registration, String number) {
		try {
			Manager manager = managerRepository.findByRegistration(registration);
			if (manager != null) {
				CheckingAccount obj = checkingAccountRepository.findByAccountNumber(number);
				checkingAccountRepository.delete(obj);
			} else {
				throw new NullPointerException("Matrícula de Gerente inexistente");
			}
		} catch (InvalidDataAccessApiUsageException e) {
			throw new ResourceNotFoundExcception(number);
		}
	}

	// tratamento feito
	public void deleteAccountSpecial(String registration, String number) {
		try {
			Manager manager = managerRepository.findByRegistration(registration);
			if (manager != null) {
				SpecialAccount obj = specialAccountRepository.findByAccountNumber(number);
				specialAccountRepository.delete(obj);
			} else {
				throw new NullPointerException("Matrícula de Gerente inexistente");
			}
		} catch (InvalidDataAccessApiUsageException e) {
			throw new ResourceNotFoundExcception(number);
		}
	}

	public Customer updateCustomer(String registration, Customer customerUpdate) {

		Manager manager = managerRepository.findByRegistration(registration);
		if (manager != null) {
			String customerCpf = customerUpdate.getCpf();
			String customerEmail = customerUpdate.getEmail();

			Customer ctm = customerRepository.findByCpf(customerCpf);
			
			if (ctm == null || ctm.equals(customerUpdate)) {

				Customer ctmEmail = customerRepository.findByEmail(customerEmail);

				if (ctmEmail == null || ctmEmail.equals(customerUpdate)) {
					return customerRepository.save(customerUpdate);
				} else {
					throw new AlreadExistException(customerEmail);
				}

			} else {
				throw new AlreadExistException(customerCpf);
			}

		} else {
			throw new NullPointerException("Matrícula de Gerente inexistente");
		}
	}

	public Account getAccount(String numberAccount) {
		Account account = checkingAccountRepository.findByAccountNumber(numberAccount);
		if (account != null) {
			return account;
		} else {
			account = specialAccountRepository.findByAccountNumber(numberAccount);
			Optional<Account> special = Optional.ofNullable(account);
			return special.orElseThrow(() -> new ResourceNotFoundExcception(numberAccount));
		}
	}

	public Account updateCreditCard(String registration, String number, CreditCard card) {
		Manager manager = managerRepository.findByRegistration(registration);
		if (manager != null) {
			Account account = getAccount(number);
			account.setCreditCard(card);

			if (account.getClass().getName().equals(CheckingAccount.class.getName())) {
				return checkingAccountRepository.save((CheckingAccount) account);
			} else {
				return specialAccountRepository.save((SpecialAccount) account);
			}
		} else {
			throw new NullPointerException("Matrícula de Gerente inexistente");
		}
	}

}
