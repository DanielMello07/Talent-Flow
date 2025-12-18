package com.example.TalentFlow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codVaga;

    private String titulo;
    private String descricao;
    private String area;
    @Builder.Default
    private boolean ativa = true;

    @ManyToOne
    @JoinColumn(name = "codEmpresa")
    private Empresa empresa;

    @OneToMany(mappedBy = "vaga", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CandidatoVaga> candidaturas = new ArrayList<>();
}
