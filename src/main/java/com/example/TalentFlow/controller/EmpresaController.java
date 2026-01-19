package com.example.TalentFlow.controller;

import com.example.TalentFlow.dto.*;
import com.example.TalentFlow.model.Empresa;
import com.example.TalentFlow.model.Sessao;
import com.example.TalentFlow.repository.EmpresaRepository;
import com.example.TalentFlow.repository.SessaoRepository;
import com.example.TalentFlow.repository.VagaRepository;
import com.example.TalentFlow.service.EmpresaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empresas")
@CrossOrigin(origins = "*")
public class EmpresaController {

    private final EmpresaRepository empresaRepository;
    private final SessaoRepository sessaoRepository;
    private final VagaRepository vagaRepository;
    private final EmpresaService empresaService;
    private final BCryptPasswordEncoder passwordEncoder;

    public EmpresaController(EmpresaRepository empresaRepository, EmpresaService empresaService, BCryptPasswordEncoder passwordEncoder, SessaoRepository sessaoRepository, VagaRepository vagaRepository) {
        this.empresaRepository = empresaRepository;
        this.passwordEncoder = passwordEncoder;
        this.sessaoRepository = sessaoRepository;
        this.vagaRepository = vagaRepository;
        this.empresaService = empresaService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody EmpresaLoginRequestDTO loginDto) {
        Empresa empresa = empresaRepository.findByEmailCorporativo(loginDto.getEmailCorporativo())
                .filter(e -> passwordEncoder.matches(loginDto.getSenha(), e.getSenha()))
                .orElse(null);
        if (empresa == null){
            return ResponseEntity.status(401).body("E-mail ou senha inválidos.");
        }

        Sessao novaSessao = new Sessao();
        novaSessao.setEmpresa(empresa);
        sessaoRepository.save(novaSessao);

        EmpresaLoginResponseDTO response = new EmpresaLoginResponseDTO(
                novaSessao.getToken(),
                empresa.getCodEmpresa()
        );

        return ResponseEntity.ok(response);

    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody EmpresaRequestDTO dto) {
        // 1. Validação de duplicidade
        if (empresaRepository.existsByEmailCorporativo(dto.getEmailCorporativo())) {
            return ResponseEntity.badRequest().body("Email já cadastrado.");
        }

        // 2. Mapeamento DTO -> Entity
        Empresa empresa = new Empresa();

        String senha = dto.getSenha();
        String senhaCriptografada = passwordEncoder.encode(senha);

        empresa.setNome(dto.getNome());
        empresa.setCnpj(dto.getCnpj());
        empresa.setEmailCorporativo(dto.getEmailCorporativo());
        empresa.setSenha(senhaCriptografada);
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

    @GetMapping("/contagem/vagas/{codEmpresa}") // URL fica: /empresas/contagem/1
    public long contarVagasAtivas(@PathVariable Long codEmpresa) {
        // Note que agora não usamos @RequestBody, pois o ID vem na URL
        return empresaService.VagasAtivas(codEmpresa);
    }

    @GetMapping("/contagem/candidaturas/{codEmpresa}") // URL fica: /empresas/contagem/1
    public long candidaturasVagasAtivas(@PathVariable Long codEmpresa) {
        // Note que agora não usamos @RequestBody, pois o ID vem na URL
        return empresaService.CandidaturasVagasAtivas(codEmpresa);
    }

}