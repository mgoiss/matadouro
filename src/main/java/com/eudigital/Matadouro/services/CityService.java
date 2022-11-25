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

import com.eudigital.matadouro.DTO.CityDTO;
import com.eudigital.matadouro.entities.City;
import com.eudigital.matadouro.entities.SlaughterValueLog;
import com.eudigital.matadouro.repositories.CityRepository;
import com.eudigital.matadouro.repositories.SlaughterValueLogRepository;
import com.eudigital.matadouro.services.exceptions.DatabaseException;
import com.eudigital.matadouro.services.exceptions.ResourceNotFoundException;

@Service
public class CityService {

	@Autowired
	private CityRepository repository;
	
	@Autowired
	private SlaughterValueLogRepository slaughterValueLogRepository;
	
	@Transactional(readOnly = true)
	public Page<CityDTO> findAllPaged(PageRequest pageRequest) {
		Page<City> list = repository.findAll(pageRequest);
		
		return list.map(x -> new CityDTO(x));
	}
	
	@Transactional(readOnly = true)
	public CityDTO findById(Long id) {
		Optional<City> obj = repository.findById(id);
		City entity = obj.orElseThrow(() -> new ResourceNotFoundException("Cidade não encontrada")); 
		
		return new CityDTO(entity);
	}

	@Transactional
	public CityDTO insert(CityDTO dto) {
		City entity = new City();
		
		entity.setName(dto.getName());
		entity.setState(dto.getState());
		entity.setCurrentSlaughterPrice(dto.getCurrentSlaughterPrice());
		
		entity = repository.save(entity);
		
		//Gravando Log
		slaughterValueLogRepository.save(new SlaughterValueLog(entity.getCurrentSlaughterPrice(), entity.getName()));
		
		return new CityDTO(entity);
	}

	@Transactional
	public CityDTO update(Long id, CityDTO dto) {
		try {
			City entity = repository.getReferenceById(id);
			
			//Pegando o preço anterior
			Double otherPrice = entity.getCurrentSlaughterPrice();

			entity.setName(dto.getName());
			entity.setState(dto.getState());
			entity.setCurrentSlaughterPrice(dto.getCurrentSlaughterPrice());
			
			entity = repository.save(entity);
			
			if (Double.compare(otherPrice, entity.getCurrentSlaughterPrice()) != 0) {
				//Gravando Log
				slaughterValueLogRepository.save(new SlaughterValueLog(entity.getCurrentSlaughterPrice(), entity.getName()));
			}
			
			return new CityDTO(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Cidade com id " + id +" não encontrada");
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Cidade com id " + id +" não encontrada");
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Violação de integridade");
		}
	}
}
