package com.eudigital.matadouro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eudigital.matadouro.entities.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

}
