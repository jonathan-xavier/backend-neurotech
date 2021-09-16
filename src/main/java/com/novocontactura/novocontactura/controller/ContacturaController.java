package com.novocontactura.novocontactura.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.novocontactura.novocontactura.model.Contactura;
import com.novocontactura.novocontactura.repository.ContacturaRepository;

@CrossOrigin()
@RestController
@RequestMapping({"/contactura"})
public class ContacturaController {
	
	
	@Autowired
	private ContacturaRepository repository;

	//listar todos
	
	@GetMapping
	public List<?> findAll() {
		return repository.findAllContactura();
	}
	
	//find by name
	@GetMapping("filtro/{name}")
	public List<Contactura> find(@PathVariable String name){
		return repository.findByName(name);
	}
	
	
	
	//findby id
	
	@GetMapping(value = "{id}")
	public ResponseEntity<?> findById(@PathVariable long id){
			return repository.findById(id)
					.map(record -> ResponseEntity.ok().body(record))
					.orElse(ResponseEntity.notFound().build());
	}
	
	//save
	@PostMapping
	public Contactura create(@RequestBody Contactura contactura) {
			return repository.save(contactura);
	}
	
	//update
	
	@PutMapping(value = "{id}")
	public ResponseEntity<?> update(@PathVariable long id, @RequestBody Contactura contactura){
		return repository.findById(id)
				.map(record ->{
					record.setName(contactura.getName());
					record.setEmail(contactura.getEmail());
					record.setPhone(contactura.getPhone());
					Contactura update = repository.save(record);
					return ResponseEntity.ok().body(update);
				}).orElse(ResponseEntity.notFound().build());
	}
	
	
	
	//deletar
	@DeleteMapping(path = {"/{id}"})
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> delete(@PathVariable long id){
		return repository.findById(id)
				.map(record ->{
					 repository.deleteById(id);
					 return ResponseEntity.ok().body("deletado com sucesso!");
					
				}).orElse(ResponseEntity.notFound().build());
				
	}
	
	
	
	
	
	
	
}
