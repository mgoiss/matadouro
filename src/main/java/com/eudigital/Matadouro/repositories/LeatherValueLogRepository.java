package com.eudigital.matadouro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eudigital.matadouro.entities.LeatherValueLog;

@Repository
public interface LeatherValueLogRepository extends JpaRepository<LeatherValueLog, Long> {

}
