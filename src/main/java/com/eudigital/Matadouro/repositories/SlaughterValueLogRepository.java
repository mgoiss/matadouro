package com.eudigital.matadouro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eudigital.matadouro.entities.SlaughterValueLog;

@Repository
public interface SlaughterValueLogRepository extends JpaRepository<SlaughterValueLog, Long> {

}
