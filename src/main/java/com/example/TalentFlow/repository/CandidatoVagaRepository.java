package com.example.TalentFlow.repository;

import com.example.TalentFlow.model.Candidato;
import com.example.TalentFlow.model.CandidatoVaga;
import com.example.TalentFlow.model.CandidatoVagaId;
import com.example.TalentFlow.model.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CandidatoVagaRepository extends JpaRepository<CandidatoVaga, CandidatoVagaId> {
    boolean existsByCandidatoAndVaga(Candidato candidato, Vaga vaga);

    List<CandidatoVaga> findByCandidatoCodCandidato(Long codCandidato);
    List<CandidatoVaga> findByVagaCodVaga(Long codVaga);

    @Query("SELECT cv FROM CandidatoVaga cv WHERE cv.vaga.codVaga = :codVaga ORDER BY cv.dataAplicacao ASC")
    List<CandidatoVaga> findByVagaOrderByDataAplicacaoAsc(@Param("codVaga") Long codVaga);

    @Query("SELECT cv FROM CandidatoVaga cv WHERE cv.vaga.empresa.codEmpresa = :codEmpresa ORDER BY cv.dataAplicacao ASC")
    List<CandidatoVaga> findCandidaturasPorEmpresaOrderByData(@Param("codEmpresa") Long codEmpresa);
}
