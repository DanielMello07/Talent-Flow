package com.example.TalentFlow.controller;

import com.example.TalentFlow.dto.AlterarStatusCandidatoVagaDTO;
import com.example.TalentFlow.dto.CandidatoVagaRequestDTO;
import com.example.TalentFlow.model.CandidatoVaga;
import com.example.TalentFlow.service.CandidatoVagaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidato-vaga")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CandidatoVagaController {

    private final CandidatoVagaService service;

    @PostMapping
    public CandidatoVaga candidatar(@RequestBody CandidatoVagaRequestDTO dto) {
        return service.candidatar(dto.getCodCandidato(), dto.getCodVaga());
    }

    @GetMapping("/candidatura-candidato")
    public List<CandidatoVaga> listarPorCandidato(@RequestBody Long codCandidato) {
        return service.listarPorCandidato(codCandidato);
    }

    @GetMapping("/candidatura-empresa")
    public List<CandidatoVaga> listarPorVaga(
            @RequestBody Long codVaga,
            @RequestBody Long codEmpresa) {

        return service.listarPorVaga(codVaga, codEmpresa);
    }

    @PutMapping("/candidatura-status")
    public ResponseEntity<CandidatoVaga> alterarStatus(
            @RequestBody Long codEmpresa,
            @RequestBody AlterarStatusCandidatoVagaDTO dto) {

        CandidatoVaga atualizado = service.alterarStatus(
                codEmpresa,
                dto.getCodCandidato(),
                dto.getCodVaga(),
                dto.getNovoStatus()
        );

        return ResponseEntity.ok(atualizado);
    }

    @GetMapping("/candidaturas/{codVaga}")
    public Long CandidaturasVaga(@PathVariable Long codVaga){
        return service.QtdeCandidaturas(codVaga);
    }


}

