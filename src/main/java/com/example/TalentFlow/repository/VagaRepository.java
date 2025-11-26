package com.example.TalentFlow.repository;

import com.example.TalentFlow.model.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VagaRepository extends JpaRepository<Vaga, Long> {
    List<Vaga> findByAtivaTrue(); // Vagas abertas
    List<Vaga> findByEmpresaCodEmpresa(Long codEmpresa);
    List<Vaga> findByAreaIgnoreCase(String area);
}
