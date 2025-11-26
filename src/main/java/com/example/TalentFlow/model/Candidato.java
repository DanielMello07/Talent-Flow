package com.example.TalentFlow.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Candidato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codCandidato;
    private String nomeCompleto;
    private String cpf;
    private String email;
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