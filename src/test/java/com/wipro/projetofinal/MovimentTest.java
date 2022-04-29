package com.wipro.projetofinal;

import static org.junit.Assert.*;

import java.util.Objects;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.wipro.projetofinal.entities.Moviment;
import com.wipro.projetofinal.entities.enums.MovimentDescription;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class MovimentTest {

	@Test
	public void moviment_test() {
		Moviment moviment = new Moviment(1.0,"478473594143955" ,"863032057630184", 
				MovimentDescription.TRANSFERENCIA_ENVIADA);
	}
	@Test
	public void moviment_test2() {
		Moviment moviment = new Moviment(1.0,"863032057630184", 
				MovimentDescription.TRANSFERENCIA_ENVIADA);
	}
	@Test
	public void getAccountOrigin_test() {
		Moviment moviment = new Moviment();
		moviment.getAccountOrigin();
	}
	@Test
	public void getMovimentDate_test() {
		Moviment moviment = new Moviment();
		moviment.getMovimentDate();
	}
	@Test
	public void getValue_test() {
		Moviment moviment = new Moviment();
		moviment.getValue();
	}
	@Test
	public void getAccountDestination_test() {
		Moviment moviment = new Moviment();
		moviment.getAccountDestination();
	}
	@Test
	public void getMovimentDescription_test() {
		Moviment moviment = new Moviment();
		moviment.getMovimentDescription();
	}
	@Test
	public void getId_test() {
		Moviment moviment = new Moviment();
		moviment.getId();
	}
	@Test
	public void hashCode_test() {
		Moviment moviment = new Moviment();
		moviment.hashCode();
	}
	@Test
	public void equals_test() {
		Moviment moviment = new Moviment();
		moviment.equals(moviment);
	}
	@Test
	public void equals_null_test() {
		Moviment moviment = new Moviment();
		moviment.equals(null);
	}
	
	@Test
	public void set_value_test() {
		double value = 1.0;
		Moviment moviment = new Moviment();
		moviment.setValue(value);
	}
	@Test
	public void set_set_moviment_description_test() {
		
		Moviment moviment = new Moviment();
		moviment.setMovimentDescription(MovimentDescription.DEPOSITO);
	}
	@Test
	public void set_account_destination_test() {
		String acc = "478473594143955";
		Moviment moviment = new Moviment();
		moviment.setAccountDestination(acc);
	}
	
	

}
