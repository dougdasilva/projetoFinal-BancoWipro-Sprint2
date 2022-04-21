package com.wipro.projetofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.projetofinal.entities.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
	Manager findByRegistration(String registration);
}
