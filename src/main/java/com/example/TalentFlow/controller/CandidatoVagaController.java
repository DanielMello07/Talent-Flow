package com.example.TalentFlow.controller;

import com.example.TalentFlow.model.CandidatoVaga;
import com.example.TalentFlow.service.CandidatoVagaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidaturas")
public class CandidatoVagaController {

    private final CandidatoVagaService candidatoVagaService;

    public CandidatoVagaController(CandidatoVagaService candidatoVagaService) {
        this.candidatoVagaService = candidatoVagaService;
    }

    @PostMapping("/{codCandidato}/{codVaga}")
    public ResponseEntity<?> candidatar(
            @PathVariable Long codCandidato,
            @PathVariable Long codVaga) {

        return candidatoVagaService.candidatar(codCandidato, codVaga);
    }
}
