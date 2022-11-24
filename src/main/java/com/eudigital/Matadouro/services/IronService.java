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

import com.eudigital.matadouro.DTO.IronDTO;
import com.eudigital.matadouro.DTO.IronInsertDTO;
import com.eudigital.matadouro.entities.Iron;
import com.eudigital.matadouro.entities.Person;
import com.eudigital.matadouro.repositories.IronRepository;
import com.eudigital.matadouro.repositories.PersonRepository;
import com.eudigital.matadouro.services.exceptions.DatabaseException;
import com.eudigital.matadouro.services.exceptions.ResourceNotFoundException;

@Service
public class IronService {

	@Autowired
	private IronRepository repository;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Transactional(readOnly = true)
	public Page<IronDTO> findAllPaged(PageRequest pageRequest) {
		Page<Iron> list = repository.findAll(pageRequest);
		
		return list.map(x -> new IronDTO(x));
	}
	
	@Transactional(readOnly = true)
	public IronDTO findById(Long id) {
		Optional<Iron> obj = repository.findById(id);
		Iron entity = obj.orElseThrow(() -> new ResourceNotFoundException("Ferro não encontrada")); 
		
		return new IronDTO(entity);
	}

	@Transactional
	public IronDTO insert(IronInsertDTO dto) {
		Iron entity = new Iron();
		
		entity.setId(dto.getId());
		
		//Pegando o Person no Banco				
		Person person = personRepository.getReferenceById(dto.getPerson().getId());
		entity.setPerson(person);
		
		entity = repository.save(entity);
		
		return new IronDTO(entity);
	}

	@Transactional
	public IronDTO update(Long id, IronDTO dto) {
		try {
			Iron entity = repository.getReferenceById(id);

			entity.setId(dto.getId());
			
			entity = repository.save(entity);
			return new IronDTO(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Ferro com id " + id +" não encontrada");
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Ferro com id " + id +" não encontrada");
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Violação de integridade");
		}
	}
}
