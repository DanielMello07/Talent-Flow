package com.example.TalentFlow.repository;

import com.example.TalentFlow.model.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {
    List<Vaga> findByAtivaTrue();
    List<Vaga> findByEmpresaCodEmpresa(Long codEmpresa);
    List<Vaga> findByAreaIgnoreCase(String area);

    long countByEmpresa_CodEmpresa(long codEmpresa);
}

