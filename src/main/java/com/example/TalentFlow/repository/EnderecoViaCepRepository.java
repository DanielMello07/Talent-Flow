package com.example.TalentFlow.repository;

import com.example.TalentFlow.model.EnderecoViaCep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoViaCepRepository extends JpaRepository<EnderecoViaCep, String> {
}
