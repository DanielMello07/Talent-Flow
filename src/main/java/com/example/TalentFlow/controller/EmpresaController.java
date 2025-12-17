package com.example.TalentFlow.controller;

import com.example.TalentFlow.dto.EmpresaLoginDTO;
import com.example.TalentFlow.dto.EmpresaRequestDTO;
import com.example.TalentFlow.dto.EmpresaResponseDTO;
import com.example.TalentFlow.model.Empresa;
import com.example.TalentFlow.repository.EmpresaRepository;
import com.example.TalentFlow.service.EmpresaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresas")
@CrossOrigin(origins = "*")
public class EmpresaController {

    private final EmpresaService empresaService;
    private final EmpresaRepository empresaRepository;

    public EmpresaController(EmpresaService empresaService, EmpresaRepository empresaRepository) {
        this.empresaService = empresaService;
        this.empresaRepository = empresaRepository;
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
    public ResponseEntity<?> login(@RequestBody EmpresaLoginDTO .................loginDto) {
        return empresaRepository.findByEmailCorporativo(loginDto.getEmail())
                .filter(empresa -> empresa.getSenha().equals(loginDto.getSenha())) // Nota: Use BCrypt em produção
                .map(empresa -> ResponseEntity.ok(toResponseDTO(empresa)))
                .orElse(ResponseEntity.status(401).body("E-mail ou senha inválidos."));
    }
}
