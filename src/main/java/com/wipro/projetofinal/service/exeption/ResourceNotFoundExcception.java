package com.wipro.projetofinal.service.exeption;

public class ResourceNotFoundExcception extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public ResourceNotFoundExcception(Object number){
            super("Recurso Não Encontrado, Número da Conta: " +  number);
        }
        
        
}
