package com.eudigital.matadouro.DTO;

import com.eudigital.matadouro.entities.Branch;

public class BranchDTO {

	private Long id;
	private String name;
	private String CNPJ;
	private Double leatherPrice;
	
	public BranchDTO() {
	}

	public BranchDTO(Long id, String name, String cNPJ, Double leatherPrice) {
		this.id = id;
		this.name = name;
		CNPJ = cNPJ;
		this.leatherPrice = leatherPrice;
	}
	
	public BranchDTO(Branch entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		CNPJ = entity.getCNPJ();
		this.leatherPrice = entity.getLeatherPrice();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCNPJ() {
		return CNPJ;
	}

	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}

	public Double getLeatherPrice() {
		return leatherPrice;
	}

	public void setLeatherPrice(Double leatherPrice) {
		this.leatherPrice = leatherPrice;
	}
}
