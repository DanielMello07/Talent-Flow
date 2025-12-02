package com.example.TalentFlow.service;

import com.example.TalentFlow.dto.CandidatoVagaResponseDTO;
import com.example.TalentFlow.model.Candidato;
import com.example.TalentFlow.model.CandidatoVaga;
import com.example.TalentFlow.model.CandidatoVagaId;
import com.example.TalentFlow.model.Vaga;
import com.example.TalentFlow.repository.CandidatoRepository;
import com.example.TalentFlow.repository.CandidatoVagaRepository;
import com.example.TalentFlow.repository.VagaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CandidatoVagaService {

    private final CandidatoRepository candidatoRepository;
    private final VagaRepository vagaRepository;
    private final CandidatoVagaRepository candidatoVagaRepository;

    public CandidatoVagaService(
            CandidatoRepository candidatoRepository,
            VagaRepository vagaRepository,
            CandidatoVagaRepository candidatoVagaRepository) {

        this.candidatoRepository = candidatoRepository;
        this.vagaRepository = vagaRepository;
        this.candidatoVagaRepository = candidatoVagaRepository;
    }

    public ResponseEntity<?> candidatar(Long codCandidato, Long codVaga) {

        var candidato = candidatoRepository.findById(codCandidato);
        if (candidato.isEmpty()) {
            return ResponseEntity.status(404).body("Candidato não encontrado");
        }

        var vaga = vagaRepository.findById(codVaga);
        if (vaga.isEmpty()) {
            return ResponseEntity.status(404).body("Vaga não encontrada");
        }

        CandidatoVagaId id = new CandidatoVagaId(codCandidato, codVaga);

        if (candidatoVagaRepository.existsById(id)) {
            return ResponseEntity.status(409).body("Candidato já inscrito nesta vaga");
        }

        CandidatoVaga cv = CandidatoVaga.builder()
                .id(id)
                .candidato(candidato.get())
                .vaga(vaga.get())
                .dataAplicacao(LocalDate.now())
                .status("Inscrito")
                .build();

        // Salva no banco
        candidatoVagaRepository.save(cv);

        // Cria o DTO para responder sem gerar loop infinito
        CandidatoVagaResponseDTO responseDTO = new CandidatoVagaResponseDTO(cv);

        return ResponseEntity.ok(responseDTO);
    }
}
