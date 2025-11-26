package com.example.TalentFlow.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CandidatoResponseDTO {
    private Long codCandidato;
    private String nomeCompleto;
    private String email;
    private LocalDateTime dataCadastro;
    private String statusConta;

    private String rua;
    private String numero;
    private String bairro;
    private String complemento;
    private String cidade;
    private String estado;
    private String cep;

    private String areaInteresse;
}
