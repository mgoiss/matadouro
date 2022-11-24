package com.eudigital.matadouro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eudigital.matadouro.entities.Iron;

@Repository
public interface IronRepository extends JpaRepository<Iron, Long> {

}
