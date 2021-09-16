package com.novocontactura.novocontactura.controller;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
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


@CrossOrigin()
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
	
	//login
	@RequestMapping("/login")
	@GetMapping
	public List<String> login(HttpServletRequest request) throws UnsupportedEncodingException {
		//String token = request.getHeader("Authorization").substring("Basic".length()).trim();
		//return token;
		
		//estudo criptografia
		//byte[] bytes = "dale é isso".getBytes("UTF-8");
		//String usernameEncoded = Base64.getEncoder().encodeToString(bytes);
		//byte[] decoded = Base64.getDecoder().decode(usernameEncoded);
		//System.out.println(new String(decoded, StandardCharsets.UTF_8));
		
		String authorization = request.getHeader("Authorization").substring("Basic".length()).trim();
		byte[] baseCred = Base64.getDecoder().decode(authorization);
		String credentialsParsed = new String(baseCred, StandardCharsets.UTF_8);
		String[] values = credentialsParsed.split(":", 2);
		ContacturaUser user = repository.findByUsername(values[0]);
		
		//String token = request.getHeader("Autthorization").substring("Basic".length()).trim();
		//poderia ser assim.
		//ArrayList<String> lista = new ArrayList<String>();
		//lista.add(Boolean.toString(user.isAdmin()));
		//lista.add(token);
		
		//return lista;
		return Arrays.asList(Boolean.toString(user.isAdmin()), authorization);
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
	@PreAuthorize("hasRole('ADMIN')")
	public ContacturaUser save(@RequestBody ContacturaUser user) {
		user.setPassword(criptografarSenha(user.getPassword()));
		return repository.save(user);
	}
	
	
	//editar usuário
	@PutMapping(value = "{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> update(@PathVariable long id,
			@RequestBody ContacturaUser user){
		return repository.findById(id)
				.map(record ->{
					record.setName(user.getName());
					record.setUsername(user.getUsername());
					record.setPassword(criptografarSenha(user.getPassword()));
					record.setAdmin(user.isAdmin());
					
					ContacturaUser update = repository.save(record);
					return ResponseEntity.ok().body(update);
				}).orElse(ResponseEntity.notFound().build());
	}
	
	//deletar usuário
	
	@DeleteMapping(value = "{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> delete(@PathVariable long id){
		return repository.findById(id)
				.map(record ->{
					repository.deleteById(id);
					return ResponseEntity.ok().body("Id de numero "+id + ", Deletado com sucesso!");
				}).orElse(ResponseEntity.notFound().build());
	}
	
	
	private String criptografarSenha(String senha) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String passwordCrypt = passwordEncoder.encode(senha);
		return passwordCrypt;
	}
	
	
	
	
	
	
	
	
	

}
