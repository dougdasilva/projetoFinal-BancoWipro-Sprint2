package com.wipro.projetofinal.service.exeption;

public class NumberException extends RuntimeException {

	  private static final long serialVersionUID = 1L;

	  public NumberException (Object number) {
		  super("Valor "+number+" inválido");
	  }
}
