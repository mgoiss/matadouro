package com.eudigital.Matadouro.DTO;

import com.eudigital.Matadouro.entities.City;

public class CityDTO {

	private Long id;
	private String name;
	private String state;
	private Double currentSlaughterPrice;
	
	public CityDTO() {
		
	}
	
	public CityDTO(Long id, String name, String state, Double currentSlaughterPrice) {
		this.id = id;
		this.name = name;
		this.state = state;
		this.currentSlaughterPrice = currentSlaughterPrice;
	}
	
	public CityDTO(City entitie) {
		this.id = entitie.getId();
		this.name = entitie.getName();
		this.state = entitie.getState();
		this.currentSlaughterPrice = entitie.getCurrentSlaughterPrice();
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Double getCurrentSlaughterPrice() {
		return currentSlaughterPrice;
	}

	public void setCurrentSlaughterPrice(Double currentSlaughterPrice) {
		this.currentSlaughterPrice = currentSlaughterPrice;
	}
}
