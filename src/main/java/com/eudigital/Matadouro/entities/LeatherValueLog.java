package com.eudigital.matadouro.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "tb_leather_value_log")
public class LeatherValueLog implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double leatherPrice;
	private String nameBranch;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant updatedAt;
	
	public LeatherValueLog() {
	}

	public LeatherValueLog( Double leatherPrice, String nameBranch) {
		this.leatherPrice = leatherPrice;
		this.nameBranch = nameBranch;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getLeatherPrice() {
		return leatherPrice;
	}

	public void setLeatherPrice(Double leatherPrice) {
		this.leatherPrice = leatherPrice;
	}

	public String getNameBranch() {
		return nameBranch;
	}

	public void setNameBranch(String nameBranch) {
		this.nameBranch = nameBranch;
	}
	
	@PrePersist
	public void preUpdate() {
		updatedAt = Instant.now();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LeatherValueLog other = (LeatherValueLog) obj;
		return Objects.equals(id, other.id);
	}
}
