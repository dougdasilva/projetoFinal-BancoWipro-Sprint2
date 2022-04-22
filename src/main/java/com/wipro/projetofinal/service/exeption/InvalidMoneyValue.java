package com.wipro.projetofinal.service.exeption;

public class InvalidMoneyValue extends RuntimeException {

	  private static final long serialVersionUID = 1L;

	  public InvalidMoneyValue (Object number) {
		super("Valor "+number+" inválido");

	  }
}
