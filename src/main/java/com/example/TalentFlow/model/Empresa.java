package com.example.TalentFlow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codEmpresa;

    private String nome;

    @Column(unique = true)
    private String cnpj; // Login antigo removido

    private String descricao;
    private String contatoRecrutador;

    // --- NOVOS CAMPOS DE SEGURANÇA ---
    @Column(unique = true, nullable = false)
    private String emailCorportativo; // Novo Login

    @JsonIgnore // Nunca enviar a senha no JSON
    private String senha; // Hash BCrypt

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Vaga> vagas = new ArrayList<>();
}