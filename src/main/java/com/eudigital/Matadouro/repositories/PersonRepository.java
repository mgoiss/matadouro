package com.eudigital.matadouro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eudigital.matadouro.entities.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
