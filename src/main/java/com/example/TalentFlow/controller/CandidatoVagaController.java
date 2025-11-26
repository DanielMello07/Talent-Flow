package com.example.TalentFlow.controller;

import com.example.TalentFlow.model.CandidatoVaga;
import com.example.TalentFlow.service.CandidatoVagaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidaturas")
public class CandidatoVagaController {

    private final CandidatoVagaService candidatoVagaService;

    public CandidatoVagaController(CandidatoVagaService candidatoVagaService) {
        this.candidatoVagaService = candidatoVagaService;
    }

    @PostMapping("/candidatar")
    public CandidatoVaga candidatar(@RequestParam Long codCandidato, @RequestParam Long codVaga) {
        return candidatoVagaService.candidatar(codCandidato, codVaga);
    }

    @GetMapping("/vaga/{codVaga}")
    public List<CandidatoVaga> listarCandidatosDaVaga(@PathVariable Long codVaga) {
        return candidatoVagaService.listarCandidatosDaVaga(codVaga);
    }

    @GetMapping("/candidato/{codCandidato}")
    public List<CandidatoVaga> listarVagasDoCandidato(@PathVariable Long codCandidato) {
        return candidatoVagaService.listarVagasDoCandidato(codCandidato);
    }

    @GetMapping("/empresa/{codEmpresa}")
    public List<CandidatoVaga> listarCandidaturasPorEmpresa(@PathVariable Long codEmpresa) {
        return candidatoVagaService.listarCandidaturasPorEmpresa(codEmpresa);
    }
}
