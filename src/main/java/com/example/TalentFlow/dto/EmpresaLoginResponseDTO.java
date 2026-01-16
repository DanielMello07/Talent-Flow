package com.example.TalentFlow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmpresaLoginResponseDTO {
    private String token;
    private Long codEmpresa;
}
