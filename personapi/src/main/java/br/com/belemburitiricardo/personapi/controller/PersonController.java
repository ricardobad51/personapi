package br.com.belemburitiricardo.personapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.belemburitiricardo.personapi.dto.response.MessageResponseDTO;
import br.com.belemburitiricardo.personapi.entity.Person;
import br.com.belemburitiricardo.personapi.repository.PersonRepository;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {
	
	private PersonRepository personRepository;
	
	//injeção de dependencia (IoC)
	
	@Autowired(required = true)
	public PersonController(PersonRepository personRepository) {
		super();
		this.personRepository = personRepository;
	}

	
	@PostMapping
	public MessageResponseDTO createPerson(@RequestBody Person person) {
		
		Person savedPerson = personRepository.save(person);
		
		return MessageResponseDTO
				.builder()
				.build();
	}


}
