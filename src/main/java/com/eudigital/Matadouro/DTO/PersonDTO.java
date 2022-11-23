package com.eudigital.matadouro.DTO;

import java.io.Serializable;
import com.eudigital.matadouro.entities.Person;

public class PersonDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String tel;
	private String type;
	
	public PersonDTO() {
	}

	public PersonDTO(Long id, String name, String tel, String type) {
		super();
		this.id = id;
		this.name = name;
		this.tel = tel;
		this.type = type;
	}
	
	public PersonDTO(Person entities) {
		this.id = entities.getId();
		this.name = entities.getName();
		this.tel = entities.getTel();
		this.type = entities.getType();
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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
