package com.novocontactura.novocontactura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.novocontactura.novocontactura.model.ContacturaUser;

public interface UserRepository extends JpaRepository<ContacturaUser, Long> {
	
	//pesquisa customizada
	@Query(value = "SELECT * FROM contactura_user", nativeQuery = true)
	public List<ContacturaUser> findAllUser();

}
