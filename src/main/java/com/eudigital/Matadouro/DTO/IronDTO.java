package com.eudigital.matadouro.DTO;

import com.eudigital.matadouro.entities.Iron;

public class IronDTO {

	private Long id;
	
	public IronDTO() {
	}

	public IronDTO(Long id) {
		this.id = id;
	}
	
	public IronDTO(Iron entitie) {
		this.id = entitie.getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
