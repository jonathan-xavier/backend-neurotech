package com.novocontactura.novocontactura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.novocontactura.novocontactura.model.ContacturaUser;

public interface UserRepository extends JpaRepository<ContacturaUser, Long> {

}
