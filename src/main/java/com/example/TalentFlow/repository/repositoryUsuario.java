package com.example.TalentFlow.repository;

import com.example.TalentFlow.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RepositoryUsuario extends JpaRepository<Usuario, Long>{
}