package com.example.TalentFlow.dto;

import lombok.Data;
import java.sql.Date;

public class UsuarioRequestDTO {
    private Long codUsuario;
    private String nomeCompleto;
    private String email;
    private String senha;
    private String tipoUsuario;
    private Date dataCadastro;
    private String statusConta;
}