package com.wipro.projetofinal.entities.enums;

public enum CardLevel {

	BRONZE(1),
	PRATA(2),
	OURO(3);

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
