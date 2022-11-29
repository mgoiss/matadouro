package com.eudigital.Matadouro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eudigital.Matadouro.entities.Iron;

@Repository
public interface IronRepository extends JpaRepository<Iron, Long> {

}
