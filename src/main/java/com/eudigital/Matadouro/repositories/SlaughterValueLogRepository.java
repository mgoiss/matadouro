package com.eudigital.Matadouro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eudigital.Matadouro.entities.SlaughterValueLog;

@Repository
public interface SlaughterValueLogRepository extends JpaRepository<SlaughterValueLog, Long> {

}
