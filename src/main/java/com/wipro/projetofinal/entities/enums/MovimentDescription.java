package com.wipro.projetofinal.entities.enums;

public enum MovimentDescription {

	SAQUE(0),
	DEPOSITO(1),
	TRANSFERENCIA_ENVIADA(2),
	TRANSFERENCIA_RECEBIDA(3);

	private Integer code;

	private MovimentDescription(int code){
		this.code = code;
	}

	public Integer getCode(){
		return this.code;
	}
	public static MovimentDescription valueOf(int value){
		for (MovimentDescription code:
				MovimentDescription.values()) {
			if(code.getCode()==value) {return code;}
		}
		throw new IllegalArgumentException("Codigo invalido");
	}
}
