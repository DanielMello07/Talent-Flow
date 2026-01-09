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

    @GetMapping("/candidato/{codCandidato}")
    public List<CandidatoVaga> listarPorCandidato(@PathVariable Long codCandidato) {
        return service.listarPorCandidato(codCandidato);
    }

    @GetMapping("/vaga/{codVaga}/empresa/{codEmpresa}")
    public List<CandidatoVaga> listarPorVaga(
            @PathVariable Long codVaga,
            @PathVariable Long codEmpresa) {

        return service.listarPorVaga(codVaga, codEmpresa);
    }

    @PutMapping("/status/empresa/{codEmpresa}")
    public ResponseEntity<CandidatoVaga> alterarStatus(
            @PathVariable Long codEmpresa,
            @RequestBody AlterarStatusCandidatoVagaDTO dto) {

        CandidatoVaga atualizado = service.alterarStatus(
                codEmpresa,
                dto.getCodCandidato(),
                dto.getCodVaga(),
                dto.getNovoStatus()
        );

        return ResponseEntity.ok(atualizado);
    }


}

