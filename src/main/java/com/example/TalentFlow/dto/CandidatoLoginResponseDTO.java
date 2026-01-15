package com.example.TalentFlow.dto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CandidatoLoginResponseDTO {
    private String token;
    private Long codCandidato;
    private String nomeCompleto;
    private String areaInteresse;
}
