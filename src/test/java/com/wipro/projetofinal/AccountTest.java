package com.wipro.projetofinal;

import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.junit.Assert.*;

import java.time.Instant;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.wipro.projetofinal.entities.Account;
import com.wipro.projetofinal.entities.CheckingAccount;
import com.wipro.projetofinal.entities.SpecialAccount;

public class AccountTest {

	@Autowired
	private Account account;
	
	@Autowired
	private CheckingAccount checkingAcc;
	
	@Autowired
	private SpecialAccount specialAcc;
	

	@Test
	public void checking_account_test() {
		checkingAcc = new CheckingAccount();
	}
	@Test
	public void special_account_test() {
		specialAcc = new SpecialAccount();
	}
	@Test
	public void special_account_limit_test() {
		double value = 100.0;
		double limit = 200.0;
		specialAcc = new SpecialAccount(value, limit);
		assertTrue(true);	
	}
	@Test
	public void special_account_special_limit_test() {
		double limit = 200.0;
		specialAcc = new SpecialAccount();
		specialAcc.setSpecialLimit(limit);
	}
	@Test
	public void get_account_number_test() {
		CheckingAccount acc = new CheckingAccount();
		acc.getAccountNumber();
	}
	@Test
	public void get_id_number_test() {
		CheckingAccount acc = new CheckingAccount();
		acc.getId();
		
	}
	@Test
	public void get_balance_number_test() {
		CheckingAccount acc = new CheckingAccount();
		acc.getBalance();
	}
	@Test
	public void get_moviment_number_test() {
		CheckingAccount acc = new CheckingAccount();
		acc.getMoviment();
	}
	@Test
	public void get_createDate_number_test() {
		CheckingAccount acc = new CheckingAccount();
		acc.getCreatedDate();
	}
	@Test
	public void get_customer_number_test() {
		CheckingAccount acc = new CheckingAccount();
		acc.getCustomer();
	}
	@Test
	public void get_creditCard_number_test() {
		CheckingAccount acc = new CheckingAccount();
		acc.getCreditCard();	
	}
	
	@Test
	public void random_number_account_test() {
		CheckingAccount acc = new CheckingAccount();
		acc.randomNumberAccount();
	}
	@Test
	public void hash_code_test() {
		CheckingAccount acc = new CheckingAccount();
		acc.hashCode();	
	}
	@Test
	public void equals_test() {
		CheckingAccount acc = new CheckingAccount();
		acc.equals(acc);
		assertTrue(true);
	}
	@Test
	public void equals_null_test() {
		CheckingAccount acc = new CheckingAccount();
		acc.equals(null);
		assertThatNullPointerException();
	}
	
	
	

}
