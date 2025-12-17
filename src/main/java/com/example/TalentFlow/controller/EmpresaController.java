package com.example.TalentFlow.controller;

import com.example.TalentFlow.dto.*;
import com.example.TalentFlow.model.Empresa;
import com.example.TalentFlow.repository.EmpresaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empresas")
@CrossOrigin(origins = "*")
public class EmpresaController {

    private final EmpresaRepository empresaRepository;

    public EmpresaController(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody EmpresaLoginDTO loginDto) {
        return empresaRepository.findByEmailCorporativo(loginDto.getEmail())
                .filter(empresa -> empresa.getSenha().equals(loginDto.getSenha()))
                .map(empresa -> ResponseEntity.ok((Object) toResponseDTO(empresa)))
                .orElse(ResponseEntity.status(401).body("E-mail ou senha inválidos."));
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody EmpresaRequestDTO dto) {
        // 1. Validação de duplicidade
        if (empresaRepository.existsByEmailCorporativo(dto.getEmailCorporativo())) {
            return ResponseEntity.badRequest().body("Email já cadastrado.");
        }

        // 2. Mapeamento DTO -> Entity
        Empresa empresa = new Empresa();
        empresa.setNome(dto.getNome());
        empresa.setCnpj(dto.getCnpj());
        empresa.setEmailCorporativo(dto.getEmailCorporativo());
        empresa.setSenha(dto.getSenha());
        empresa.setDescricao(dto.getDescricao());
        empresa.setContatoRecrutador(dto.getContatoRecrutador());

        // 3. Persistência
        Empresa empresaSalva = empresaRepository.save(empresa);

        // 4. Retorno seguro (ResponseDTO)
        return ResponseEntity.status(201).body(toResponseDTO(empresaSalva));
    }

    private EmpresaResponseDTO toResponseDTO(Empresa e) {
        EmpresaResponseDTO dto = new EmpresaResponseDTO();
        dto.setCodEmpresa(e.getCodEmpresa());
        dto.setNome(e.getNome());
        dto.setCnpj(e.getCnpj());
        dto.setEmailCorporativo(e.getEmailCorporativo()); // Adicionado para o Front saber quem logou
        dto.setDescricao(e.getDescricao());
        dto.setContatoRecrutador(e.getContatoRecrutador());
        return dto;
    }
}