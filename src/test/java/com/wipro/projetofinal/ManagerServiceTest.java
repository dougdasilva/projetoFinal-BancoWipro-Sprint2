package com.wipro.projetofinal;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.wipro.projetofinal.entities.CheckingAccount;
import com.wipro.projetofinal.entities.Customer;
import com.wipro.projetofinal.entities.Manager;
import com.wipro.projetofinal.repository.CustomerRepository;
import com.wipro.projetofinal.repository.ManagerRepository;
import com.wipro.projetofinal.service.ManageService;
import com.wipro.projetofinal.service.exeption.AlreadExistException;
import com.wipro.projetofinal.service.exeption.InvalidValueException;

@SpringBootTest
@RunWith(SpringRunner.class)

@WebAppConfiguration
public class ManagerServiceTest {
	
	//private PasswordEncoder encoder;

	//@Autowired
	//private ManagerRepository managerRepository;
	
	//@Autowired
	//private CustomerRepository customerRepository;

	//private ManageService managerService = new ManageService(new BCryptPasswordEncoder(), managerRepository, customerRepository);
    //private ManageService managerService = new ManageService(encoder, managerRepository, customerRepository);
	
	@Autowired
	private ManageService managerService;

	//sucesso ao salvar manager
	@Test(expected = Exception.class)
	//@Test
	public void managerService_save_manager_test() throws Exception{
		Manager manager = new Manager("Maria Joaquina", "30611298040", "mjoao@gmail.com", "1232345", "12abc456");		
		//assertThrows(Exception.class, () -> managerService.saveManager(manager));
		managerService.saveManager(manager);
	}
	
	//forçar senha incorreta
	@Test
	public void managerService_password_manager_test()  {
		Manager manager = new Manager("João masco", "45627120070", "jmasco@gmail.com", "1", "12abc4567");	
		System.out.println(manager.getPassword());
		assertThrows(InvalidValueException.class, ()-> managerService.saveManager(manager));
		
	}
	
	//forçar cpf incorreto
	@Test
	public void managerService_cpf_manager_test(){
		Manager manager = new Manager("Maria Joaquina", "30611298040", "mjoao@gmail.com", "1232345", "12abc456");			
		assertThrows(AlreadExistException.class, () ->  managerService.saveManager(manager));
	}
	
	//forçar email incorreto
	@Test
	public void managerService_email_manager_test(){
		Manager manager = new Manager("Maria Joaquina", "98319878020", "mjoao@gmail.com", "1232345", "12abc456");			
		assertThrows(AlreadExistException.class, () ->  managerService.saveManager(manager));
	}
	
	//forçar matrícula inexistente
	@Test
	public void managerService_registration_manager_test(){
		Manager manager = new Manager("Maria Joaquina", "18735072083", "mj@gmail.com", "1232345", "12abc456");			
		assertThrows(AlreadExistException.class, () ->  managerService.saveManager(manager));
	}
	
	//forçar sucesso
	@Test
	public void managerService_save_Checking_account_success_test() {
		String registration = "12abc456";
		Customer customer = new Customer();
		customer.setName("Fabricio Nogueira");
		customer.setCpf("41698819072");
		customer.setEmail("fabricio@email.com");
		customer.setPassword("12345678");
		CheckingAccount account = new CheckingAccount();
		account.setCustomer(customer);
		assertEquals(account.getCustomer().getCpf(),managerService.saveCheckingAccount(registration, account).getCustomer().getCpf());
	}
	
	//forçar senha incorreta
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
	
	//Forçar manager inexistente
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

}
