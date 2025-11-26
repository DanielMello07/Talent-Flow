package com.example.TalentFlow.dto;

import lombok.Data;
import java.sql.Date;
import java.time.LocalDateTime;

@Data
public class CandidatoRequestDTO {
    private Long codCandidato;
    private String nomeCompleto;
    private String email;
    private String cpf;
    private String numeroTelefone;
    private String areaInteresse;
    private String formacaoAcademica;
    private String experienciaProfissional;
    private String enderecoCep;
    private String enderecoRua;
    private String enderecoNumero;
    private String enderecoComplemento;
    private String enderecoBairro;
    private String enderecoCidade;
    private String enderecoEstado;
    private LocalDateTime dataCadastro;
    private String statusConta;
}