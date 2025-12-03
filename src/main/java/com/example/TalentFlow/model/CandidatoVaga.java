package com.example.TalentFlow.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "candidato_vaga")
public class CandidatoVaga {

    @EmbeddedId
    private CandidatoVagaId id;

    @ManyToOne
    @MapsId("codCandidato")
    @JoinColumn(name = "cod_candidato")
    private Candidato candidato;

    @ManyToOne
    @MapsId("codVaga")
    @JoinColumn(name = "cod_vaga")
    private Vaga vaga;

    private LocalDate dataAplicacao;
    private String status; // ex: INSCRITO, SELECIONADO, REJEITADO
}
