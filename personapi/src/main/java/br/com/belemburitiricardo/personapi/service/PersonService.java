package br.com.belemburitiricardo.personapi.service;

import br.com.belemburitiricardo.personapi.dto.response.MessageResponseDTO;
import br.com.belemburitiricardo.personapi.exception.PersonNotFoundException;
import br.com.belemburitiricardo.personapi.dto.mapper.PersonMapper;
import br.com.belemburitiricardo.personapi.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;



import br.com.belemburitiricardo.personapi.dto.mapper.PersonMapper;
import br.com.belemburitiricardo.personapi.dto.request.PersonDTO;
import br.com.belemburitiricardo.personapi.entity.Person;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
//@AllArgsConstructor(onConstructor = @__(@Autowired))//criar um contrutor, excluindo o existente
public class PersonService {
	
	private PersonRepository personRepository;
	
	private final PersonMapper personMapper = PersonMapper.INSTANCE;

	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public MessageResponseDTO createPerson(PersonDTO personDTO) {

		Person personToSave = personMapper.toModel(personDTO);

		Person savedPerson = personRepository.save(personToSave);
		return createMessageResponse(savedPerson.getId(), "--> You Have Created Person with ID: ");

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


	public void delete(Long id) throws PersonNotFoundException{
		verifyIfExists(id);

		personRepository.deleteById(id);
	}

	public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
		verifyIfExists(id);

		Person personToUpdate = personMapper.toModel(personDTO);

		Person updatedPerson = personRepository.save(personToUpdate);
		return createMessageResponse(updatedPerson.getId(), "-> You Have Updated Person with ID: ");
	}

	private Person verifyIfExists(Long id) throws PersonNotFoundException {
		return personRepository.findById(id)
				.orElseThrow(() -> new PersonNotFoundException(id));
	}

	private MessageResponseDTO createMessageResponse(Long id,String message) {
		return MessageResponseDTO
				.builder()
				.message(message + id)
				.build();
	}
}
