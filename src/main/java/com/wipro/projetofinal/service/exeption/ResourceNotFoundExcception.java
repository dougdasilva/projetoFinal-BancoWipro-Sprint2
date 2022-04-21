package com.wipro.projetofinal.service.exeption;

public class ResourceNotFoundExcception extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public ResourceNotFoundExcception(Object number){
            super("Resource not found. Number Account " +  number);
        }
}
