package com.wipro.projetofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.projetofinal.entities.CheckingAccount;
import com.wipro.projetofinal.entities.Customer;

@Repository
public interface CheckingAccountRepository extends JpaRepository<CheckingAccount, Long>{
	
    CheckingAccount findByAccountNumber(String accountNumber);
    CheckingAccount findByCustomer(Customer customer);
}
