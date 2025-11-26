package com.example.TalentFlow.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidatoVagaId implements Serializable {

    @Column(name = "codCandidato")
    private Long codCandidato;

    @Column(name = "codVaga")
    private Long codVaga;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CandidatoVagaId)) return false;
        CandidatoVagaId that = (CandidatoVagaId) o;
        return Objects.equals(codCandidato, that.codCandidato) &&
                Objects.equals(codVaga, that.codVaga);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codCandidato, codVaga);
    }
}
