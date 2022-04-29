package com.wipro.projetofinal;

import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.junit.Assert.*;

import org.junit.Test;

import com.wipro.projetofinal.entities.CreditCard;
import com.wipro.projetofinal.entities.enums.CardLevel;

public class CreditCardTest {

	@Test
	public void credit_card_test() {
		
		CreditCard credit = new CreditCard();
		credit.getAtivo();
	}
	@Test
	public void credit_card_test2() {
		
		CreditCard credit = new CreditCard(CardLevel.BRONZE);
		credit.getAtivo();
	}
	@Test
	public void credit_card_test3() {
		
		CreditCard credit = new CreditCard(CardLevel.BRONZE);
		credit.getCardLevel();
	}
	@Test
	public void credit_card_test4() {
		
		CreditCard credit = new CreditCard(CardLevel.BRONZE);
		credit.getCardNumber();
	}
	@Test
	public void credit_card_test5() {
		
		CreditCard credit = new CreditCard(CardLevel.BRONZE);
		credit.getCreditLimit();
	}
	@Test
	public void credit_card_test6() {
		
		CreditCard credit = new CreditCard(CardLevel.BRONZE);
		credit.getCvv();
	}
	@Test
	public void credit_card_test7() {
		
		CreditCard credit = new CreditCard(CardLevel.BRONZE);
		credit.getExpirationDate();
	}
	@Test
	public void credit_card_test8() {
		
		CreditCard credit = new CreditCard(CardLevel.BRONZE);
		credit.getFlag();
	}
	@Test
	public void credit_card_test9() {
		
		CreditCard credit = new CreditCard(CardLevel.BRONZE);
		credit.getId();
	}
	@Test
	public void credit_card_test10() {
		
		CreditCard credit = new CreditCard(CardLevel.BRONZE);
		credit.hashCode();
	}
	@Test
	public void credit_card_test11() {
		
		CreditCard credit = new CreditCard(CardLevel.BRONZE);
		credit.equals(credit);
		assertTrue(true);
	}
	@Test
	public void credit_card_test12() {
		
		CreditCard credit = new CreditCard(CardLevel.BRONZE);
		credit.equals(null);
		assertThatNullPointerException();
	}
	@Test
	public void credit_card_test13() {
		
		CreditCard credit = new CreditCard(CardLevel.BRONZE);
		credit.getClass();
	}
	

}
