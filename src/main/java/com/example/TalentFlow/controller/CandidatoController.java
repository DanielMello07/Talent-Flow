package com.example.TalentFlow.controller;

import com.example.TalentFlow.dto.CandidatoLoginDTO;
import com.example.TalentFlow.dto.CandidatoRequestDTO;
import com.example.TalentFlow.dto.CandidatoResponseDTO;
import com.example.TalentFlow.repository.CandidatoRepository;

import com.example.TalentFlow.service.CandidatoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.example.TalentFlow.dto.*;
import com.example.TalentFlow.model.Candidato;

import java.util.List;

@RestController
@RequestMapping("/candidatos")
@CrossOrigin(origins = "*") // Adicione esta linha
public class CandidatoController {

    private final CandidatoService candidatoService;
    private final CandidatoRepository candidatoRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public CandidatoController(CandidatoService candidatoService, CandidatoRepository candidatoRepository, BCryptPasswordEncoder passwordEncoder) {
        this.candidatoService = candidatoService;
        this.candidatoRepository = candidatoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping ("/listar")
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

    private CandidatoResponseDTO toResponseDTO(Candidato candidato) {
        CandidatoResponseDTO dto = new CandidatoResponseDTO();

        // Mapeamento de campos de Identificação e Contato
        dto.setCodCandidato(candidato.getCodCandidato()); // Ou candidato.getCodCandidato() se o nome no Model for este
        dto.setNomeCompleto(candidato.getNomeCompleto());
        dto.setEmail(candidato.getEmail());

        // Mapeamento de Auditoria e Status
        dto.setDataCadastro(candidato.getDataCadastro());
        dto.setStatusConta(candidato.getStatusConta());

        // Mapeamento de Endereço
        dto.setRua(candidato.getRua());
        dto.setNumero(candidato.getNumero());
        dto.setBairro(candidato.getBairro());
        dto.setComplemento(candidato.getComplemento());
        dto.setCidade(candidato.getCidade());
        dto.setEstado(candidato.getEstado());
        dto.setCep(candidato.getCep());

        // Mapeamento Profissional
        dto.setAreaInteresse(candidato.getAreaInteresse());

        return dto;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CandidatoLoginDTO loginDto) {
        return candidatoRepository.findByEmail(loginDto.getEmail())
                .filter(candidato -> {
                    // 3. USE O MATCHES EM VEZ DO EQUALS
                    return passwordEncoder.matches(loginDto.getSenha(), candidato.getSenha());
                })
                .map(candidato -> ResponseEntity.ok((Object) toResponseDTO(candidato)))
                .orElse(ResponseEntity.status(401).body("E-mail ou senha inválidos."));
    }
}
