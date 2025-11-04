package com.example.TalentFlow.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codUsuario;
    private String nomeCompleto;
    private String email;
    private String senha;
    private String tipoUsuario;
    private date dataCadastro;
    private String statusConta;

    system.out.print("Oiii");
}