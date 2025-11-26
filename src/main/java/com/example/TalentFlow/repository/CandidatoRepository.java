package com.example.TalentFlow.repository;

import com.example.TalentFlow.model.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
    Optional<Candidato> findByEmailAndSenha(String email, String senha);
    boolean existsByEmail(String email);
}
