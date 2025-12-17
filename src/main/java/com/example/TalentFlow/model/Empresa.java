package com.example.TalentFlow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data // Gera getters, setters, toString, equals e hashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codEmpresa;

    private String nome;
    private String cnpj;
    private String descricao;
    private String contatoRecrutador;

    // NOVO CAMPO PARA LOGIN
    @Column(unique = true)
    private String emailCorporativo;

    private String senha;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Vaga> vagas = new ArrayList<>();
}