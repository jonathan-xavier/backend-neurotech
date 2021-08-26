package com.novocontactura.novocontactura.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.novocontactura.novocontactura.model.ContacturaUser;
import com.novocontactura.novocontactura.repository.UserRepository;



@RestController
@RequestMapping({"/user"})
public class UserController {
	
	@Autowired
	private UserRepository repository;
	
	//metodo para buscar todos.
	@GetMapping
	public List<ContacturaUser> findAll(){
		return repository.findAllUser();
	}
	
	
	//fazer busca por id
	@GetMapping(value = "{id}")
	public ResponseEntity<?> findById(@PathVariable long id){
		return repository.findById(id)
				.map(record ->{
					return ResponseEntity.ok().body(record);
				}).orElse(ResponseEntity.notFound().build());
	}
	
	//salvar usuário
	
	@PostMapping
	public ContacturaUser save(@RequestBody ContacturaUser user) {
		return repository.save(user);
	}
	
	
	//editar usuário
	@PutMapping(value = "{id}")
	public ResponseEntity<?> update(@PathVariable long id,
			@RequestBody ContacturaUser user){
		return repository.findById(id)
				.map(record ->{
					record.setName(user.getName());
					record.setUsername(user.getUsername());
					record.setPassword(user.getPassword());
					record.setAdmin(user.isAdmin());
					
					ContacturaUser update = repository.save(record);
					return ResponseEntity.ok().body(update);
				}).orElse(ResponseEntity.notFound().build());
	}
	
	//deletar usuário
	
	@DeleteMapping(value = "{id}")
	public ResponseEntity<?> delete(@PathVariable long id){
		return repository.findById(id)
				.map(record ->{
					repository.deleteById(id);
					return ResponseEntity.ok().body("Id de numero "+id + ", Deletado com sucesso!");
				}).orElse(ResponseEntity.notFound().build());
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
