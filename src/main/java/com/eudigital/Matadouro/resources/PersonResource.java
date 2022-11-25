package com.eudigital.matadouro.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.eudigital.matadouro.DTO.PersonDTO;
import com.eudigital.matadouro.services.PersonService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/persons")
@Api(value = "Persons")
public class PersonResource {
	
	@Autowired
	private PersonService service;
	
	@GetMapping
	@ApiOperation(value = "Listagem de todas as pessoas")
	public ResponseEntity<Page<PersonDTO>> findAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy
			) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<PersonDTO> list = service.findAllPaged(pageRequest);

		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Listagem de pessoa por ID")
	public ResponseEntity<PersonDTO> findById(@PathVariable Long id) {
		PersonDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	@ApiOperation(value = "Inserção de uma nova pessoa")
	public ResponseEntity<PersonDTO> insert(@RequestBody PersonDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping(value = "/{id}")
	@ApiOperation(value = "Atualizando uma pessoa")
	public ResponseEntity<PersonDTO> update(@PathVariable Long id, @RequestBody PersonDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	@ApiOperation(value = "Deletando uma pessoa")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
