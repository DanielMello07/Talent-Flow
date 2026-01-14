package com.example.TalentFlow.model;

import com.example.TalentFlow.model.Candidato;
import com.example.TalentFlow.model.Empresa;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "sessao")
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSessao;

    @Column(nullable = false, unique = true)
    private String token;

    private LocalDateTime dataCriacao;
    private LocalDateTime dataExpiracao;

    // Relacionamentos (Opcionais, podem ser nulos dependendo de quem logou)
    @ManyToOne
    @JoinColumn(name = "cod_candidato")
    private Candidato candidato;

    @ManyToOne
    @JoinColumn(name = "cod_empresa")
    private Empresa empresa;

    // Construtor padrão para gerar o token automaticamente
    public Sessao() {
        this.token = UUID.randomUUID().toString(); // Gera um código único como: "550e8400-e29b..."
        this.dataCriacao = LocalDateTime.now();
        this.dataExpiracao = LocalDateTime.now().plusMinutes(30); // Sessão dura 8 horas
    }

    // Getters e Setters...
}