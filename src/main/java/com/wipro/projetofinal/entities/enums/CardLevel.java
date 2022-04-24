package com.wipro.projetofinal.entities.enums;

public enum CardLevel {

	BRONZE(0),
	PRATA(1),
	OURO(2);

	private Integer code;

	private CardLevel(int value){
		this.code = value;
	}

	public Integer getCode(){
		return this.code;
	}


	public static CardLevel valueOf(int value){
		for (CardLevel code:
				CardLevel.values()) {
			if(code.getCode()==value) {return code;}
		}
		throw new IllegalArgumentException("Codigo invalido");
	}
}
