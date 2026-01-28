package com.example.TalentFlow.service;

import com.example.TalentFlow.exception.DataIntegrityViolationException;
import com.example.TalentFlow.exception.ResourceNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.TalentFlow.dto.CandidatoRequestDTO;
import com.example.TalentFlow.dto.CandidatoResponseDTO;
import com.example.TalentFlow.model.Candidato;
import com.example.TalentFlow.repository.CandidatoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CandidatoService {

    private final CandidatoRepository candidatoRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public CandidatoService(CandidatoRepository candidatoRepository) {
        this.candidatoRepository = candidatoRepository;
        this.passwordEncoder = new BCryptPasswordEncoder(); // hash
    }

    public List<CandidatoResponseDTO> listar() {
        return candidatoRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public CandidatoResponseDTO buscar(Long id) {
        Candidato c = candidatoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidato não encontrado com o ID: " + id));
        return toResponseDTO(c);
    }

    public CandidatoResponseDTO salvar(CandidatoRequestDTO dto) {
        if (candidatoRepository.existsByEmail(dto.getEmail())) {
            throw new DataIntegrityViolationException("Email já cadastrado:" + dto.getEmail());
        }
        Candidato c = new Candidato();
        c.setNomeCompleto(dto.getNomeCompleto());
        c.setEmail(dto.getEmail());
        c.setSenha(passwordEncoder.encode(dto.getSenha())); // hash
        c.setDataCadastro(LocalDateTime.now());
        c.setStatusConta("ATIVA");
        c.setRua(dto.getRua());
        c.setNumero(dto.getNumero());
        c.setBairro(dto.getBairro());
        c.setComplemento(dto.getComplemento());
        c.setCidade(dto.getCidade());
        c.setEstado(dto.getEstado());
        c.setCep(dto.getCep());
        c.setAreaInteresse(dto.getAreaInteresse());
        return toResponseDTO(candidatoRepository.save(c));
    }

    public CandidatoResponseDTO atualizar(Long id, CandidatoRequestDTO dto) {
        Candidato c = candidatoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidato não encontrado"));
        c.setNomeCompleto(dto.getNomeCompleto());
        c.setEmail(dto.getEmail());
        if (dto.getSenha() != null && !dto.getSenha().isBlank()) {
            c.setSenha(passwordEncoder.encode(dto.getSenha()));
        }
        c.setRua(dto.getRua());
        c.setNumero(dto.getNumero());
        c.setBairro(dto.getBairro());
        c.setComplemento(dto.getComplemento());
        c.setCidade(dto.getCidade());
        c.setEstado(dto.getEstado());
        c.setCep(dto.getCep());
        c.setAreaInteresse(dto.getAreaInteresse());
        return toResponseDTO(candidatoRepository.save(c));
    }

    public void deletar(Long id) {
        candidatoRepository.deleteById(id);
    }

    public Candidato login(String email, String senha) {
        return candidatoRepository.findByEmail(email)
                .filter(c -> passwordEncoder.matches(senha, c.getSenha()))
                .orElse(null);
    }

    private CandidatoResponseDTO toResponseDTO(Candidato c) {
        CandidatoResponseDTO dto = new CandidatoResponseDTO();
        dto.setCodCandidato(c.getCodCandidato());
        dto.setNomeCompleto(c.getNomeCompleto());
        dto.setEmail(c.getEmail());
        dto.setDataCadastro(c.getDataCadastro());
        dto.setStatusConta(c.getStatusConta());
        dto.setRua(c.getRua());
        dto.setNumero(c.getNumero());
        dto.setBairro(c.getBairro());
        dto.setComplemento(c.getComplemento());
        dto.setCidade(c.getCidade());
        dto.setEstado(c.getEstado());
        dto.setCep(c.getCep());
        dto.setAreaInteresse(c.getAreaInteresse());
        return dto;
    }
}
