package br.com.belemburitiricardo.personapi.service;

import br.com.belemburitiricardo.personapi.dto.response.MessageResponseDTO;
import br.com.belemburitiricardo.personapi.mapper.PersonMapper;
import br.com.belemburitiricardo.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;

//import br.com.belemburitiricardo.personapi.mapper.mapper.PersonMapper;
import br.com.belemburitiricardo.personapi.dto.request.PersonDTO;
import br.com.belemburitiricardo.personapi.entity.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
	
	private PersonRepository personRepository;
	
	private final PersonMapper personMapper = PersonMapper.INSTANCE;

	@Autowired
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public MessageResponseDTO createPerson(PersonDTO personDTO) {

		Person personToSave = personMapper.toModel(personDTO);

		Person savedPerson = personRepository.save(personToSave);
		return MessageResponseDTO
				.builder()
				.message("-> You Have Created Person with ID: " + savedPerson.getId())
				.build();
	}

	public List<PersonDTO> listAll(){
		List<Person> allPeople = personRepository.findAll();
		return null;/* allPeople.stream()
				.map(personMapper::toDTO);*/
	}

}
