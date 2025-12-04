package com.example.TalentFlow.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codCandidato;

    private String nomeCompleto;

    @Column(unique = true)
    private String email;

    private String senha;

    private LocalDateTime dataCadastro;

    private String statusConta = "ATIVA";
    private String rua;
    private String numero;
    private String bairro;
    private String complemento;
    private String cidade;
    private String estado;
    private String cep;
    private String areaInteresse;

    @OneToMany(mappedBy = "candidato")
    private List<CandidatoVaga> candidaturas = new ArrayList<>();
}
