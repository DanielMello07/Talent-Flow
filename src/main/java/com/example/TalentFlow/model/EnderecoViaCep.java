package com.example.TalentFlow.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EnderecoViaCep {
    @Id
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
}
