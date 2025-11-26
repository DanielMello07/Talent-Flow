package com.example.TalentFlow.service;

import com.example.TalentFlow.model.Candidato;
import com.example.TalentFlow.model.CandidatoVaga;
import com.example.TalentFlow.model.Vaga;
import com.example.TalentFlow.repository.CandidatoRepository;
import com.example.TalentFlow.repository.CandidatoVagaRepository;
import com.example.TalentFlow.repository.VagaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CandidatoVagaService {

    private final CandidatoRepository candidatoRepository;
    private final VagaRepository vagaRepository;
    private final CandidatoVagaRepository candidatoVagaRepository;

    public CandidatoVagaService(CandidatoRepository candidatoRepository,
                                VagaRepository vagaRepository,
                                CandidatoVagaRepository candidatoVagaRepository) {
        this.candidatoRepository = candidatoRepository;
        this.vagaRepository = vagaRepository;
        this.candidatoVagaRepository = candidatoVagaRepository;
    }

    public CandidatoVaga candidatar(Long codCandidato, Long codVaga) {
        Candidato candidato = candidatoRepository.findById(codCandidato)
                .orElseThrow(() -> new RuntimeException("Candidato não encontrado"));
        Vaga vaga = vagaRepository.findById(codVaga)
                .orElseThrow(() -> new RuntimeException("Vaga não encontrada"));

        if (candidatoVagaRepository.existsByCandidatoAndVaga(candidato, vaga)) {
            throw new RuntimeException("Você já se candidatou a essa vaga!");
        }

        CandidatoVaga cv = new CandidatoVaga();
        cv.setCandidato(candidato);
        cv.setVaga(vaga);
        cv.setDataAplicacao(LocalDate.now());
        cv.setStatus("INSCRITO");

        return candidatoVagaRepository.save(cv);
    }

    public List<CandidatoVaga> listarCandidatosDaVaga(Long codVaga) {
        return candidatoVagaRepository.findByVagaOrderByDataAplicacaoAsc(codVaga);
    }

    public List<CandidatoVaga> listarVagasDoCandidato(Long codCandidato) {
        return candidatoVagaRepository.findByCandidatoCodCandidato(codCandidato);
    }

    public List<CandidatoVaga> listarCandidaturasPorEmpresa(Long codEmpresa) {
        return candidatoVagaRepository.findCandidaturasPorEmpresaOrderByData(codEmpresa);
    }
}
