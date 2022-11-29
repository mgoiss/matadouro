package com.eudigital.Matadouro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eudigital.Matadouro.entities.LeatherValueLog;

@Repository
public interface LeatherValueLogRepository extends JpaRepository<LeatherValueLog, Long> {

}
