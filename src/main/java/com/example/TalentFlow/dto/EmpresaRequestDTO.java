package com.example.TalentFlow.dto;

import lombok.Data;

@Data
public class EmpresaRequestDTO {
    private String nome;
    private String cnpj;
    private String descricao;
    private String contatoRecrutador;
}
