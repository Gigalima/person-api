package com.gigalima.personapi.service;

import java.util.List;
import java.util.stream.Collectors;

import com.gigalima.personapi.exception.PersonNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigalima.personapi.dto.MessageResponseDTO;
import com.gigalima.personapi.dto.request.PersonDTO;
import com.gigalima.personapi.entity.Person;
import com.gigalima.personapi.mapper.PersonMapper;
import com.gigalima.personapi.repository.PersonRepository;

@Service
public class PersonService {

	private PersonRepository personRepository;

	private final PersonMapper personMapper = PersonMapper.INSTANCE;

	@Autowired
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public MessageResponseDTO createPerson(PersonDTO personDTO) {
		Person persontoSave = personMapper.toModel(personDTO);

		Person savedPerson = personRepository.save(persontoSave);
		return MessageResponseDTO
				.builder()
				.message("Created with ID " + savedPerson.getId())
				.build();
	}

	public List<PersonDTO> listAll() {
		List <Person> allPeople = personRepository.findAll();
		return allPeople.stream()
				.map(personMapper::toDTO)
				.collect(Collectors.toList());
	}

    public PersonDTO findById(Long id) throws PersonNotFoundException {
		Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));

		return personMapper.toDTO(person);
    }
}
