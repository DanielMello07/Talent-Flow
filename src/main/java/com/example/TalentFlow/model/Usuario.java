package com.example.TalentFlow.model;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;

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
    private Date dataCadastro;
    private String statusConta;


}