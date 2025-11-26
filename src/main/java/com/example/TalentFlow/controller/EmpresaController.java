package com.example.TalentFlow.controller;

import com.example.TalentFlow.dto.EmpresaRequestDTO;
import com.example.TalentFlow.dto.EmpresaResponseDTO;
import com.example.TalentFlow.model.Empresa;
import com.example.TalentFlow.service.EmpresaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping
    public List<EmpresaResponseDTO> listar() {
        return empresaService.listar();
    }

    @GetMapping("/{id}")
    public EmpresaResponseDTO buscar(@PathVariable Long id) {
        return empresaService.buscar(id);
    }

    @PostMapping
    public EmpresaResponseDTO salvar(@RequestBody EmpresaRequestDTO dto) {
        return empresaService.salvar(dto);
    }

    @PutMapping("/{id}")
    public EmpresaResponseDTO atualizar(@PathVariable Long id, @RequestBody EmpresaRequestDTO dto) {
        return empresaService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        empresaService.deletar(id);
    }

    @PostMapping("/login")
    public Empresa login(@RequestParam String nome, @RequestParam String cnpj) {
        return empresaService.login(nome, cnpj);
    }
}
