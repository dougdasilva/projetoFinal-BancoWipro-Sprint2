package com.wipro.projetofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wipro.projetofinal.entities.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {

	Manager findByRegistration(String registration);

	Manager findByEmail(String email);

	@Query(value = "SELECT * FROM tb_managers m where (m.cpf = :cpf OR m.email= :email OR m.registration = :registration)", nativeQuery = true)
	Manager findManagerByCpfOrByEmailOrByRegistration(@Param(value = "cpf") String cpf,
			@Param(value = "email") String email, @Param(value = "registration") String registration);

}
