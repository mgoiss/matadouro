package com.eudigital.Matadouro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eudigital.Matadouro.entities.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
