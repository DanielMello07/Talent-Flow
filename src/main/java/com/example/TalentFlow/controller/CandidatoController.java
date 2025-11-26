package com.example.TalentFlow.controller;

import com.example.TalentFlow.dto.CandidatoRequestDTO;
import com.example.TalentFlow.dto.CandidatoResponseDTO;
import com.example.TalentFlow.model.Candidato;
import com.example.TalentFlow.service.CandidatoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidatos")
public class CandidatoController {

    private final CandidatoService candidatoService;

    public CandidatoController(CandidatoService candidatoService) {
        this.candidatoService = candidatoService;
    }

    @GetMapping
    public List<CandidatoResponseDTO> listar() {
        return candidatoService.listar();
    }

    @GetMapping("/{id}")
    public CandidatoResponseDTO buscar(@PathVariable Long id) {
        return candidatoService.buscar(id);
    }

    @PostMapping
    public CandidatoResponseDTO salvar(@RequestBody CandidatoRequestDTO dto) {
        return candidatoService.salvar(dto);
    }

    @PutMapping("/{id}")
    public CandidatoResponseDTO atualizar(@PathVariable Long id, @RequestBody CandidatoRequestDTO dto) {
        return candidatoService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        candidatoService.deletar(id);
    }

    @PostMapping("/login")
    public Candidato login(@RequestParam String email, @RequestParam String senha) {
        Candidato c = candidatoService.login(email, senha);
        if (c == null) throw new RuntimeException("Credenciais inválidas");
        return c;
    }
}
