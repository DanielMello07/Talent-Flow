package com.example.TalentFlow.dto;

import com.example.TalentFlow.enums.StatusCandidatoVaga;
import lombok.Data;

@Data
public class AlterarStatusCandidatoVagaDTO {
    private Long codCandidato;
    private Long codVaga;
    private StatusCandidatoVaga novoStatus;
}
