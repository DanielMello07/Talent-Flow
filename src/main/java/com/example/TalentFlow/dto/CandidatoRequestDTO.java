package com.example.TalentFlow.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CandidatoRequestDTO {
    private String nomeCompleto;
    private String email;
    private String senha;
    private String rua;
    private String numero;
    private String bairro;
    private String complemento;
    private String cidade;
    private String estado;
    private String cep;
    private String areaInteresse;
}