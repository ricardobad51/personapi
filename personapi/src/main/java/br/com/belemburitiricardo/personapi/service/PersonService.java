package br.com.belemburitiricardo.personapi.service;

import br.com.belemburitiricardo.personapi.dto.response.MessageResponseDTO;
import br.com.belemburitiricardo.personapi.exception.PersonNotFoundException;
import br.com.belemburitiricardo.personapi.mapper.PersonMapper;
import br.com.belemburitiricardo.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;



//import br.com.belemburitiricardo.personapi.mapper.mapper.PersonMapper;
import br.com.belemburitiricardo.personapi.dto.request.PersonDTO;
import br.com.belemburitiricardo.personapi.entity.Person;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
		return  allPeople.stream()//manipular
				.map(personMapper::toDTO)//reflence
				.collect(Collectors.toList());
	}

	public PersonDTO findById(Long id) throws PersonNotFoundException {
		//Optional<Person> optionalPerson = personRepository.findById(id);
		Person person = verifyIfExists(id);
		/*if (optionalPerson.isEmpty()){
			throw new personNotFoundException(id);
		}*/
		return personMapper.toDTO(person/*optionalPerson.get()*/);
	}

	private Person verifyIfExists(Long id) throws PersonNotFoundException {
		return personRepository.findById(id)
				.orElseThrow(() -> new PersonNotFoundException(id));
	}

	public void delete(Long id) throws PersonNotFoundException{
		verifyIfExists(id);

		personRepository.deleteById(id);
	}
}
