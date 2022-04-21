package com.wipro.projetofinal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.projetofinal.entities.Customer;
import com.wipro.projetofinal.repository.CustomerRepository;

@Service
public class CustomerService {

	
	@Autowired
	private CustomerRepository repository;
	
	

	
}
