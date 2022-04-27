package com.wipro.projetofinal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.wipro.projetofinal.entities.CheckingAccount;
import com.wipro.projetofinal.entities.Customer;
import com.wipro.projetofinal.entities.Manager;
import com.wipro.projetofinal.entities.SpecialAccount;
import com.wipro.projetofinal.service.ManageService;
import com.wipro.projetofinal.service.exeption.AlreadExistException;
import com.wipro.projetofinal.service.exeption.AlreadyExistAccountByCpf;
import com.wipro.projetofinal.service.exeption.InvalidValueException;
import com.wipro.projetofinal.service.exeption.ResourceNotFoundExcception;

@SpringBootTest
@RunWith(SpringRunner.class)

@WebAppConfiguration
public class ManagerServiceTest {

	// private ManageService managerService = new ManageService(new
	// BCryptPasswordEncoder(), managerRepository, customerRepository);

	@Autowired
	private ManageService managerService;

	// sucesso ao salvar manager
	@Test
	public void managerService_save_manager_test() throws Exception {
		Manager manager = new Manager("Maria Joaquina", "30611298040", "mjoao@gmail.com", "1232345", "12abc456");
		// assertThrows(Exception.class, () -> managerService.saveManager(manager));
		managerService.saveManager(manager);
	}

	// forçar senha incorreta
	@Test
	public void managerService_password_manager_test() {
		Manager manager = new Manager("João masco", "45627120070", "jmasco@gmail.com", "1", "12abc4567");
		System.out.println(manager.getPassword());
		assertThrows(InvalidValueException.class, () -> managerService.saveManager(manager));

	}

	// forçar cpf incorreto
	@Test
	public void managerService_cpf_manager_test() {
		Manager manager = new Manager("Maria Joaquina", "30611298040", "mjoao@gmail.com", "1232345", "12abc456");
		assertThrows(AlreadExistException.class, () -> managerService.saveManager(manager));
	}

	// forçar email repetido no banco
	@Test
	public void managerService_email_manager_test() {
		Manager manager = new Manager("Maria Joaquina", "98319878020", "mjoao@gmail.com", "1232345", "12abc456");
		assertThrows(AlreadExistException.class, () -> managerService.saveManager(manager));
	}

	// forçar matrícula inexistente
	@Test
	public void managerService_registration_manager_test() {
		Manager manager = new Manager("Maria Joaquina", "18735072083", "mj@gmail.com", "1232345", "12abc456");
		assertThrows(AlreadExistException.class, () -> managerService.saveManager(manager));
	}

	// forçar sucesso de conta especial
	@Test
	public void managerService_save_Special_account_success_test() {
		String registration = "12abc456";
		Customer customer = new Customer();
		customer.setName("Fabricio Nogueira");
		customer.setCpf("41698819072");
		customer.setEmail("fabricio@email.com");
		customer.setPassword("12345678");
		SpecialAccount account = new SpecialAccount();
		account.setCustomer(customer);
		assertEquals(account.getCustomer().getCpf(),
				managerService.saveSpecialAccount(registration, account).getCustomer().getCpf());
	}

	// forçar sucesso
	@Test
	public void managerService_save_Checking_account_success_test() {
		String registration = "12abc456";
		Customer customer = new Customer();
		customer.setName("Fabricio Nogueira");
		customer.setCpf("74512045023");
		customer.setEmail("fabricio@email.com");
		customer.setPassword("12345678");
		CheckingAccount account = new CheckingAccount();
		account.setCustomer(customer);
		assertEquals(account.getCustomer().getCpf(),
				managerService.saveCheckingAccount(registration, account).getCustomer().getCpf());
	}

	// forçar senha incorreta
	@Test
	public void managerService_save_Checking_account_password_test() {
		String registration = "12abc456";
		Customer customer = new Customer();
		customer.setName("Fabricio Nogueira");
		customer.setCpf("10989967042");
		customer.setEmail("fabricio@email.com");
		customer.setPassword("1");
		CheckingAccount account = new CheckingAccount();
		account.setCustomer(customer);
		assertThrows(InvalidValueException.class, () -> managerService.saveCheckingAccount(registration, account));
	}

	// Forçar manager inexistente
	@Test
	public void managerService_save_Checking_account_manager_test() {
		String registration = "1";
		Customer customer = new Customer();
		customer.setName("Fabricio Nogueira");
		customer.setCpf("10989967042");
		customer.setEmail("fabricio@email.com");
		customer.setPassword("12324234334");
		CheckingAccount account = new CheckingAccount();
		account.setCustomer(customer);
		assertThrows(NullPointerException.class, () -> managerService.saveCheckingAccount(registration, account));
	}

	// Forçar cliente com CPF repetido
	@Test
	public void managerService_save_Checking_account_CPF_test() {
		String registration = "12abc456";
		Customer customer = new Customer();
		customer.setName("Fabricio Nogueira");
		customer.setCpf("74512045023");
		customer.setEmail("fab@email.com");
		customer.setPassword("12324234334");
		CheckingAccount account = new CheckingAccount();
		account.setCustomer(customer);
		assertThrows(AlreadyExistAccountByCpf.class, () -> managerService.saveCheckingAccount(registration, account));
	}

	// Forçar salvar uma conta especial com CPF repetido
	@Test
	public void managerService_save_Special_account_CPF_test() {
		String registration = "12abc456";
		Customer customer = new Customer();
		customer.setName("Fabricio Nogueira");
		customer.setCpf("41698819072");
		customer.setEmail("fabricio2@email.com");
		customer.setPassword("12345678");
		SpecialAccount account = new SpecialAccount();
		account.setCustomer(customer);
		assertThrows(AlreadyExistAccountByCpf.class, () -> managerService.saveSpecialAccount(registration, account));
	}

	// Forçar manager inexistente para salvar conta especial
	@Test
	public void managerService_save_Special_account_manager_test() {
		String registration = "1";
		Customer customer = new Customer();
		customer.setName("João Rodrigues");
		customer.setCpf("23557584097");
		customer.setEmail("jr@email.com");
		customer.setPassword("12324234334");
		SpecialAccount account = new SpecialAccount();
		account.setCustomer(customer);
		assertThrows(NullPointerException.class, () -> managerService.saveSpecialAccount(registration, account));
	}

	// Forçar salvar conta especial com senha incoerente
	@Test
	public void managerService_save_Special_account_password_test() {
		String registration = "12abc456";
		Customer customer = new Customer();
		customer.setName("João Rodrigues");
		customer.setCpf("68297735044");
		customer.setEmail("jrd@email.com");
		customer.setPassword("1");
		SpecialAccount account = new SpecialAccount();
		account.setCustomer(customer);
		assertThrows(InvalidValueException.class, () -> managerService.saveSpecialAccount(registration, account));
	}

	// Forçar sucesso ao buscar todas as contas
	@Test
	public void managerService_findAllAccounts_test() {
		String registration = "12abc456";
		managerService.findAllAccounts(registration);
		assertTrue(true);
	}

	// Forçar erro ao buscar todas as contas com uma matricula de gerente inxistente
	@Test
	public void managerService_findAllAccounts_ManagerInexistent_test() {
		String registration = "12";
		assertThrows(NullPointerException.class, () -> managerService.findAllAccounts(registration));
	}

	// Forçar sucesso ao buscar todas as contas Correntes
	@Test
	public void managerService_findAllChecking_test() {
		String registration = "12abc456";
		managerService.findAllChecking(registration);
		assertTrue(true);
	}

	// Forçar erro ao buscar todas as contas Correntes com uma matricula de gerente
	// inxistente
	@Test(expected = NullPointerException.class)
	public void managerService_findAllChecking_ManagerInexistent_test() {
		String registration = "";
		managerService.findAllChecking(registration);
//		assertThrows(NullPointerException.class, () -> managerService.findAllChecking(registration));
	}

	// Forçar sucesso ao buscar todas as contas Especiais
	@Test
	public void managerService_findAllSpecial_test() {
		String registration = "12abc456";
		managerService.findAllSpecial(registration);
		assertTrue(true);
	}

	// Forçar erro ao buscar todas as contas Especiais com uma matricula de gerente
	// inxistente
	@Test(expected = NullPointerException.class)
	public void managerService_findAllSpecial_ManagerInexistent_test() {
		String registration = "";
		managerService.findAllSpecial(registration);
//		assertThrows(NullPointerException.class, () -> managerService.findAllChecking(registration));
	}

	// Forçar sucesso ao buscar uma conta corrente pelo seu número
	@Test
	public void managerService_findByAccountNumberChecking_test() {
		String registration = "12abc456";
		String accountNumber = "490185615332692";
		assertNotNull(managerService.findByAccountNumberChecking(registration, accountNumber));
	}

	// Forçar erro ao buscar uma conta corrente por um número inexistente
	@Test
	public void managerService_findByAccountNumberChecking_NumberInexistent_test() {
		String registration = "12abc456";
		String accountNumber = "111111111111111";
		assertThrows(ResourceNotFoundExcception.class,
				() -> managerService.findByAccountNumberChecking(registration, accountNumber));
	}

	// Forçar erro ao buscar uma conta corrente por um número inexistente
	@Test
	public void managerService_findByAccountNumberChecking_ManagerInexistent_test() {
		String registration = "";
		String accountNumber = "490185615332692";
		assertThrows(NullPointerException.class,
				() -> managerService.findByAccountNumberChecking(registration, accountNumber));
	}

	// Forçar sucesso ao buscar uma conta especial pelo seu número
	@Test
	public void managerService_findByAccountNumberSpecial_test() {
		String registration = "12abc456";
		String accountNumber = "120358604235041";
		assertNotNull(managerService.findByAccountNumberSpecial(registration, accountNumber));
	}

	// Forçar erro ao buscar uma conta especial por um número inexistente
	@Test
	public void managerService_findByAccountNumberSpecial_NumberInexistent_test() {
		String registration = "12abc456";
		String accountNumber = "111111111111111";
		assertThrows(ResourceNotFoundExcception.class,
				() -> managerService.findByAccountNumberSpecial(registration, accountNumber));
	}

	// Forçar erro ao buscar uma conta corrente por um número inexistente
	@Test
	public void managerService_findByAccountNumberSpecial_ManagerInexistent_test() {
		String registration = "";
		String accountNumber = "120358604235041";
		assertThrows(NullPointerException.class,
				() -> managerService.findByAccountNumberSpecial(registration, accountNumber));
	}

	// forçar sucesso ao deletar uma conta corrente
	@Test
	public void managerService_deleteAccountChecking_success_test() {
		String registration = "12abc456";
		Customer customer = new Customer();
		customer.setName("Beatriz Dinamarquesa");
		customer.setCpf("06330856001");
		customer.setEmail("beatriz@email.com");
		customer.setPassword("12345678");
		CheckingAccount account = new CheckingAccount();
		account.setCustomer(customer);
		managerService.saveCheckingAccount(registration, account);

		managerService.deleteAccountChecking(registration, account.getAccountNumber());
		assertTrue(true);
	}

	// Forçar erro ao tentar excluir conta corrente com Matricula de gerente
	// inexistente
	@Test
	public void managerService_deleteAccountChecking_ManagerInexistent_test() {
		String registrationValid = "12abc456";
		String registrationInvalid = "";
		Customer customer = new Customer();
		customer.setName("Ana Paula");
		customer.setCpf("51860039057");
		customer.setEmail("ana@email.com");
		customer.setPassword("12345678");
		CheckingAccount account = new CheckingAccount();
		account.setCustomer(customer);
		managerService.saveCheckingAccount(registrationValid, account);

		assertThrows(NullPointerException.class,
				() -> managerService.deleteAccountChecking(registrationInvalid, account.getAccountNumber()));

	}

	// forçar sucesso ao deletar uma conta especial
	@Test
	public void managerService_deleteAccountSpecial_success_test() {
		String registration = "12abc456";
		Customer customer = new Customer();
		customer.setName("Carla Beatriz");
		customer.setCpf("69153240030");
		customer.setEmail("carla@email.com");
		customer.setPassword("12345678");
		SpecialAccount account = new SpecialAccount();
		account.setCustomer(customer);
		managerService.saveSpecialAccount(registration, account);

		managerService.deleteAccountSpecial(registration, account.getAccountNumber());
		assertTrue(true);
	}

	// Forçar erro ao tentar excluir conta especial com Matricula de gerente
	// inexistente
	@Test
	public void managerService_deleteAccountSpecial_ManagerInexistent_test() {
		String registrationValid = "12abc456";
		String registrationInvalid = "";
		Customer customer = new Customer();
		customer.setName("Carla Beatriz");
		customer.setCpf("69153240030");
		customer.setEmail("carla@email.com");
		customer.setPassword("12345678");
		SpecialAccount account = new SpecialAccount();
		account.setCustomer(customer);
		managerService.saveSpecialAccount(registrationValid, account);

		assertThrows(NullPointerException.class,
				() -> managerService.deleteAccountSpecial(registrationInvalid, account.getAccountNumber()));

	}
}
