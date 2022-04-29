package com.wipro.projetofinal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.projetofinal.entities.Customer;
import com.wipro.projetofinal.entities.SpecialAccount;

@Repository
public interface SpecialAccountRepository extends JpaRepository<SpecialAccount, Long>{
	
    SpecialAccount findByAccountNumber(String accountNumber);
    SpecialAccount findByCustomer(Customer customer);
    List<SpecialAccount> findAllByStatus(boolean status);
}
