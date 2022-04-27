package com.wipro.projetofinal.service.exeption;

public class AlreadExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	
	public AlreadExistException(String msg) {
		super(msg);
	}
	
	
}
