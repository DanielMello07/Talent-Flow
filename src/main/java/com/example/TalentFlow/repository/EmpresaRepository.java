package com.example.TalentFlow.repository;

import com.example.TalentFlow.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    Optional<Empresa> findByNomeAndCnpj(String nome, String cnpj);
}
