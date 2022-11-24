package com.eudigital.matadouro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eudigital.matadouro.entities.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

}
