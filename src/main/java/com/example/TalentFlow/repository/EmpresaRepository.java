package com.example.TalentFlow.repository;

import com.example.TalentFlow.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    // Este método permite buscar a empresa pelo novo login (E-mail)
    Optional<Empresa> findByEmailCorporativo(String emailCorporativo);

    // Útil para validação no cadastro
    boolean existsByEmailCorporativo(String emailCorporativo);
}