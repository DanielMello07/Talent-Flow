package com.example.TalentFlow.dto;

import lombok.Data;
import java.sql.Date;

@Data
public class UsuarioResponseDTO {
    private Long codUsuario;
    private String nomeCompleto;
    private String email;
    private String tipoUsuario;
    private Date dataCadastro;
    private String statusConta;
}