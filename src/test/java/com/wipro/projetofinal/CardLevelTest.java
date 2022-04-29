package com.wipro.projetofinal;

import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wipro.projetofinal.entities.enums.CardLevel;

public class CardLevelTest {



	@Test
	public void get_code_test() {
		CardLevel card = CardLevel.OURO;
		card.getCode();
	}
	

}
