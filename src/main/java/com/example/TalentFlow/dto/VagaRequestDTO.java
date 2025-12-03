package com.example.TalentFlow.dto;

import lombok.Data;

@Data
public class VagaRequestDTO {
    private String titulo;
    private String descricao;
    private String area;
    private boolean status;
    private Long codEmpresa;
}
