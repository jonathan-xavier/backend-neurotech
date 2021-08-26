package com.novocontactura.novocontactura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.novocontactura.novocontactura.model.Contactura;

@Repository
public interface ContacturaRepository extends JpaRepository<Contactura, Long> {
	
	
	//teste para buscar por nome
	@Query(value = "SELECT * FROM contactura WHERE name = ?1", nativeQuery = true)
	public List<Contactura> findByName(String name);
	
	//listar todos
	
	@Query(value = "SELECT * FROM contactura", nativeQuery = true)
	public List<Contactura> findAllContactura();

}
