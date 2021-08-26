package com.novocontactura.novocontactura;

import java.util.stream.LongStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.novocontactura.novocontactura.model.Contactura;
import com.novocontactura.novocontactura.repository.ContacturaRepository;

@SpringBootApplication
public class NovocontacturaApplication {

	public static void main(String[] args) {
		SpringApplication.run(NovocontacturaApplication.class, args);
		
		
		
	}
	
	/*
	@Bean
	CommandLineRunner init(ContacturaRepository repository) {
			return args ->{
				//repository.deleteAll()
				
				LongStream.range(1, 50).mapToObj(id -> {
					
					Contactura c = new Contactura();
					c.setName("Contactura user" + id);
					c.setPhone("(081) 9" + id + id + id + id +  "-" + id + id + id + id );
					c.setEmail("contactura_user" + id + "@contactura.com");
					return c;
				}).map(record -> repository.save(record))
				.forEach(System.out::println);
				
			};
	}*/
}
