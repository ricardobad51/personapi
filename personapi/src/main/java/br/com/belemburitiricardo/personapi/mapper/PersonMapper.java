package br.com.belemburitiricardo.personapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;

import br.com.belemburitiricardo.personapi.dto.request.PersonDTO;
import br.com.belemburitiricardo.personapi.entity.Person;

@Mapper//(componentModel = "spring")
public interface PersonMapper {
	
	PersonMapper INSTANCE = Mappers.getMapper( PersonMapper.class );
	
	@Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
	Person toModel(PersonDTO personDTO); //convensao pra conversão
	
	PersonDTO toDTO(Person person);
	
}
