package com.example.TalentFlow.dto;

import lombok.Data;

@Data
public class EmpresaResponseDTO {
    private Long codEmpresa;
    private String nome;
    private String cnpj;
    private String descricao;
    private String contatoRecrutador;
}
