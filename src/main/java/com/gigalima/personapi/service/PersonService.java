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
		return createMessageResponse(savedPerson.getId(), "Created with ID ");
	}

	public List<PersonDTO> listAll() {
		List <Person> allPeople = personRepository.findAll();
		return allPeople.stream()
				.map(personMapper::toDTO)
				.collect(Collectors.toList());
	}

    public PersonDTO findById(Long id) throws PersonNotFoundException {
		Person person = verifyIfExists(id);

		return personMapper.toDTO(person);
    }



	public void delete(Long id) throws PersonNotFoundException {
		verifyIfExists(id);

		personRepository.deleteById(id);
	}

	public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
		verifyIfExists(id);

		Person persontoUpdate = personMapper.toModel(personDTO);

		Person updatedPerson = personRepository.save(persontoUpdate);
		return createMessageResponse(updatedPerson.getId(), "Updated with ID ");
	}

	private Person verifyIfExists(Long id) throws PersonNotFoundException {
		return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
	}

	private MessageResponseDTO createMessageResponse(Long id, String message) {
		return MessageResponseDTO
				.builder()
				.message(message + id)
				.build();
	}
}
