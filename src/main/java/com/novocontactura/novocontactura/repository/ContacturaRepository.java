package com.novocontactura.novocontactura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.novocontactura.novocontactura.model.Contactura;

@Repository
public interface ContacturaRepository extends JpaRepository<Contactura, Long> {

}
