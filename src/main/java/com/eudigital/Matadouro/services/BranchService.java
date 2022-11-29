package com.eudigital.Matadouro.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eudigital.Matadouro.DTO.BranchDTO;
import com.eudigital.Matadouro.entities.Branch;
import com.eudigital.Matadouro.entities.LeatherValueLog;
import com.eudigital.Matadouro.repositories.BranchRepository;
import com.eudigital.Matadouro.repositories.LeatherValueLogRepository;
import com.eudigital.Matadouro.services.exceptions.DatabaseException;
import com.eudigital.Matadouro.services.exceptions.ResourceNotFoundException;

@Service
public class BranchService {

	@Autowired
	private BranchRepository repository;
	
	@Autowired
	private LeatherValueLogRepository leatherValueLogRepository;
	
	@Transactional(readOnly = true)
	public Page<BranchDTO> findAllPaged(PageRequest pageRequest) {
		Page<Branch> list = repository.findAll(pageRequest);
		
		return list.map(x -> new BranchDTO(x));
	}
	
	@Transactional(readOnly = true)
	public BranchDTO findById(Long id) {
		Optional<Branch> obj = repository.findById(id);
		Branch entity = obj.orElseThrow(() -> new ResourceNotFoundException("Filial não encontrada")); 
		
		return new BranchDTO(entity);
	}

	@Transactional
	public BranchDTO insert(BranchDTO dto) {
		Branch entity = new Branch();
		
		entity.setName(dto.getName());
		entity.setCNPJ(dto.getCNPJ());
		entity.setLeatherPrice(dto.getLeatherPrice());
		
		entity = repository.save(entity);
		
		//Gravendo Log
		leatherValueLogRepository.save(new LeatherValueLog(entity.getLeatherPrice(), entity.getName()));
		
		return new BranchDTO(entity);
	}

	@Transactional
	public BranchDTO update(Long id, BranchDTO dto) {
		try {
			Branch entity = repository.getReferenceById(id);

			//Pegando o preço anterior
			Double otherPrice = entity.getLeatherPrice();
			
			entity.setName(dto.getName());
			entity.setCNPJ(dto.getCNPJ());
			entity.setLeatherPrice(dto.getLeatherPrice());
			
			entity = repository.save(entity);
			
			if (Double.compare(otherPrice, entity.getLeatherPrice()) != 0) {
				//Gravando Log
				leatherValueLogRepository.save(new LeatherValueLog(entity.getLeatherPrice(), entity.getName()));

			}
			
			return new BranchDTO(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Filial com id " + id +" não encontrada");
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Filial com id " + id +" não encontrada");
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Violação de integridade");
		}
	}
}
