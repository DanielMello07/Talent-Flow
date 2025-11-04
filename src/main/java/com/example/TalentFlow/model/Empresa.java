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
    private int cnpj;
    private String descrcao;
    private String contatoRecrutador;

}
