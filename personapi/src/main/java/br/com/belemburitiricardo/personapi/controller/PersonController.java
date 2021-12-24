package br.com.belemburitiricardo.personapi.controller;

import br.com.belemburitiricardo.personapi.dto.request.PersonDTO;
import br.com.belemburitiricardo.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import br.com.belemburitiricardo.personapi.dto.response.MessageResponseDTO;
import br.com.belemburitiricardo.personapi.entity.Person;
import br.com.belemburitiricardo.personapi.repository.PersonRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {
	
	private PersonService personService;
	
	//injeção de dependencia (IoC)
	
	@Autowired
	public PersonController(PersonService personService) {
		super();
		this.personService = personService;
	}

	
	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
	public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO) {
		
		return personService.createPerson(personDTO);
	}


}
