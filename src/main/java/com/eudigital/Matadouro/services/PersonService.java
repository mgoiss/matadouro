package com.eudigital.matadouro.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eudigital.matadouro.DTO.PersonDTO;
import com.eudigital.matadouro.entities.Person;
import com.eudigital.matadouro.repositories.PersonRepository;
import com.eudigital.matadouro.services.exceptions.DatabaseException;
import com.eudigital.matadouro.services.exceptions.ResourceNotFoundException;

@Service
public class PersonService {

	@Autowired
	private PersonRepository repository;
	
	@Transactional(readOnly = true)
	public Page<PersonDTO> findAllPaged(PageRequest pageRequest) {
		Page<Person> list = repository.findAll(pageRequest);
		
		return list.map(x -> new PersonDTO(x));
	}
	
	@Transactional(readOnly = true)
	public PersonDTO findById(Long id) {
		Optional<Person> obj = repository.findById(id);
		Person entity = obj.orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada")); 
		
		return new PersonDTO(entity);
	}

	@Transactional
	public PersonDTO insert(PersonDTO dto) {
		Person entity = new Person();
		
		entity.setName(dto.getName());
		entity.setTel(dto.getTel());
		entity.setType(dto.getType());
		
		//Alterar Lista de Ferro
		
		entity = repository.save(entity);
		
		return new PersonDTO(entity);
	}

	@Transactional
	public PersonDTO update(Long id, PersonDTO dto) {
		try {
			Person entity = repository.getReferenceById(id);

			entity.setName(dto.getName());
			entity.setTel(dto.getTel());
			entity.setType(dto.getType());
			
			entity = repository.save(entity);
			return new PersonDTO(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Pessoa com id " + id +" não encontrada");
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Pessoa com id " + id +" não encontrada");
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Violação de integridade");
		}
	}
}
