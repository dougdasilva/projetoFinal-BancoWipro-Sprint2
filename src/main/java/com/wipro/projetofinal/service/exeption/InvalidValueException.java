package com.wipro.projetofinal.service.exeption;

public class InvalidValueException extends RuntimeException {

	  private static final long serialVersionUID = 1L;

	  public InvalidValueException (Object number) {
		super("Valor "+number+" inválido");
	  }
	  
	  public InvalidValueException (String msg) {
			super(msg);
	  }
}
