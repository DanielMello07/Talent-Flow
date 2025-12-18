package com.example.TalentFlow.model;

import com.example.TalentFlow.enums.StatusCandidatoVaga;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "candidato_vaga")
public class CandidatoVaga {

    @EqualsAndHashCode.Include
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

    @Column(name = "data_aplicacao", nullable = false)
    private LocalDateTime dataAplicacao;

    @Enumerated(EnumType.STRING)
    private StatusCandidatoVaga status;

    // --- NOVO CAMPO DE ARQUIVO ---
    // Armazena o nome do arquivo físico no servidor (ex: "a1b2c3d4.pdf")
    private String nomeArquivoFisico;

    // Armazena o nome original criptografado ou mascarado (ex: "Curr...Silva.pdf")
    private String nomeArquivoOriginal;
}