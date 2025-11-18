package com.example.TalentFlow.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codEmpresa;
    private String nome;
    private int cnpj;
    private String descricao;
    private String contatoRecrutador;
}