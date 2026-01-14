package com.example.TalentFlow.repository;

import com.example.TalentFlow.model.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SessaoRepository extends JpaRepository<Sessao, Long> {
    Optional<Sessao> findByToken(String token);
    void deleteByToken(String token); // Útil para o Logout
}