package com.example.TalentFlow.controller;

import com.example.TalentFlow.dto.CandidatoRequestDTO;
import com.example.TalentFlow.dto.CandidatoResponseDTO;
import com.example.TalentFlow.service.CandidatoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidatos")
public class CandidatoController {
    private final CandidatoService service;

    public CandidatoController(CandidatoService service){
        this.service=service;
    }

    @GetMapping  // define o método GET HTTP
    public List<CandidatoResponseDTO>listar(){
        return service.listar();
    }

    @GetMapping("/{codCandidato}")  // define o método GET HTTP com parametro
    public CandidatoResponseDTO buscar(@PathVariable Long codCandidato){
        return service.buscar(codCandidato);
    }

    @PostMapping // define metodo POST HTTP
    public CandidatoResponseDTO salvar(@RequestBody CandidatoRequestDTO dto){
        return service.salvar(dto);
    }

    @PutMapping("/{codCandidato}")
    public CandidatoResponseDTO atualizar(@PathVariable Long codCandidato, @RequestBody CandidatoRequestDTO dto){
        return service.atualizar(codCandidato, dto);
    }

    @DeleteMapping("/{codCandidato}")
    public void deletar(@PathVariable Long codCandidato){
        service.deletar(codCandidato);
    }
}