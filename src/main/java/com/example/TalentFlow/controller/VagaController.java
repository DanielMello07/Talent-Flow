package com.example.TalentFlow.controller;

import com.example.TalentFlow.dto.VagaRequestDTO;
import com.example.TalentFlow.dto.VagaResponseDTO;
import com.example.TalentFlow.model.Vaga;
import com.example.TalentFlow.service.VagaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vagas")
@CrossOrigin(origins = "*")
public class VagaController {

    private final VagaService vagaService;

    public VagaController(VagaService vagaService) {
        this.vagaService = vagaService;
    }

    @GetMapping
    public List<VagaResponseDTO> listar() {
        return vagaService.listar();
    }

    @GetMapping("/{id}")
    public VagaResponseDTO buscar(@PathVariable Long id) {
        return vagaService.buscar(id);
    }

    @PostMapping
    public VagaResponseDTO salvar(@RequestBody VagaRequestDTO dto) {
        return vagaService.salvar(dto);
    }

    @PutMapping("/{id}")
    public VagaResponseDTO atualizar(@PathVariable Long id, @RequestBody VagaRequestDTO dto) {
        return vagaService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        vagaService.deletar(id);
    }

    @GetMapping("/area/{area}")
    public List<Vaga> buscarPorArea(@PathVariable String area) {
        return vagaService.buscarVagasPorArea(area);
    }

    @GetMapping("/empresa/{codEmpresa}")
    public List<Vaga> buscarPorEmpresa(@PathVariable Long codEmpresa) {
        return vagaService.buscarVagasPorEmpresa(codEmpresa);
    }

    @GetMapping("/ativas")
    public List<Vaga> listarVagasAtivas() {
        return vagaService.buscarVagasAtivas();
    }

    @PutMapping("/encerrar/{codVaga}/empresa/{codEmpresa}")
    public ResponseEntity<VagaResponseDTO> encerrarVaga(
            @PathVariable Long codVaga,
            @PathVariable Long codEmpresa) {

        Vaga vagaEncerrada = vagaService.encerrarVaga(codEmpresa, codVaga);
        return ResponseEntity.ok(vagaService.buscar(vagaEncerrada.getCodVaga()));
    }

}
