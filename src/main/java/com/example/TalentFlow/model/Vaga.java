package com.example.TalentFlow.model;

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
    private String ativa = "ATIVA";

    @ManyToOne
    @JoinColumn(name = "codEmpresa")
    private Empresa empresa;

    @OneToMany(mappedBy = "vaga", cascade = CascadeType.ALL)
    private List<CandidatoVaga> candidaturas = new ArrayList<>();
}
