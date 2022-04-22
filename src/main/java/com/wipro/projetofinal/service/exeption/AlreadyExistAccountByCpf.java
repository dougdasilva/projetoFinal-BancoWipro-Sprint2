package com.wipro.projetofinal.service.exeption;

public class AlreadyExistAccountByCpf extends RuntimeException{
	 private static final long serialVersionUID = 1L;

     public AlreadyExistAccountByCpf(Object cpf){
         super("CPF já existente para esse tipo de conta: " +  cpf);
     }
     
     public AlreadyExistAccountByCpf() {
    	 super("Registro inválido!");
     }
}
