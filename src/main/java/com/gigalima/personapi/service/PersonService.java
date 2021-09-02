package com.gigalima.personapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigalima.personapi.dto.MessageResponseDTO;
import com.gigalima.personapi.entity.Person;
import com.gigalima.personapi.repository.PersonRepository;

@Service
public class PersonService {

	private PersonRepository personRepository;

	@Autowired
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public MessageResponseDTO createPerson(Person person) {
		Person savedPerson = personRepository.save(person);
		return MessageResponseDTO.builder()
				.message("Created with ID " + savedPerson
				.getId())
				.build();
	}

}
