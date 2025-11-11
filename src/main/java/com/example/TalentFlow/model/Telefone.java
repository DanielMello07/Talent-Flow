package com.example.TalentFlow.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Telefone {
    @Id
    private int ddd;
    @Id
    private int numero;
    @Id
    private Long codUsuario;


}
