package com.example.TalentFlow.dto;

import lombok.Data;

@Data
public class VagaResponseDTO {
    private Long codVaga;
    private String titulo;
    private String descricao;
    private String area;
    private Long codEmpresa;
}
