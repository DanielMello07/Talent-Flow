package com.example.TalentFlow.dto;

import com.example.TalentFlow.enums.StatusCandidatoVaga;
import com.example.TalentFlow.model.CandidatoVaga;
import lombok.Data;

@Data
public class CandidatoVagaResponseDTO {
    private Long codCandidato;
    private Long codVaga;
    private StatusCandidatoVaga status;
    private String dataAplicacao;

    public CandidatoVagaResponseDTO(CandidatoVaga cv) {
        this.codCandidato = cv.getCandidato().getCodCandidato();
        this.codVaga = cv.getVaga().getCodVaga();
        this.status = cv.getStatus();
        this.dataAplicacao = cv.getDataAplicacao() != null ? cv.getDataAplicacao().toString() : null;
    }
}
