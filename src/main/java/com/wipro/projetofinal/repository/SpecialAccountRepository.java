package com.wipro.projetofinal.repository;

import com.wipro.projetofinal.entities.CheckingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.projetofinal.entities.SpecialAccount;

@Repository
public interface SpecialAccountRepository extends JpaRepository<SpecialAccount, Long>{
    SpecialAccount findByAccountNumber(String accountNumber);
}
