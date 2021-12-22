package br.com.belemburitiricardo.personapi.service;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.belemburitiricardo.personapi.dto.mapper.PersonMapper;
import br.com.belemburitiricardo.personapi.dto.request.PersonDTO;
import br.com.belemburitiricardo.personapi.dto.response.MessageResponseDTO;
import br.com.belemburitiricardo.personapi.entity.Person;
import br.com.belemburitiricardo.personapi.repository.PersonRepository;

public class PersonService {
	
private PersonRepository personRepository;	
	
	private PersonMapper personMapper;	

	@Autowired
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public MessageResponseDTO createPerson(PersonDTO personDTO) {

		Person personToSave = personMapper.toModel(personDTO);

		Person savedPerson = personRepository.save(personToSave);
		return MessageResponseDTO.builder().message("Created Person with ID " + savedPerson.getId()).build();

	}

}
