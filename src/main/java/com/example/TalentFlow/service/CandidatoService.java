package com.example.TalentFlow.service;

import com.example.TalentFlow.dto.CandidatoRequestDTO;
import com.example.TalentFlow.dto.CandidatoResponseDTO;
import com.example.TalentFlow.model.Candidato;
import com.example.TalentFlow.repository.CandidatoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidatoService {

    private final CandidatoRepository candidatoRepository;

    public CandidatoService(CandidatoRepository candidatoRepository) {
        this.candidatoRepository = candidatoRepository;
    }

    public List<CandidatoResponseDTO> listar() {
        return candidatoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public CandidatoResponseDTO buscar(Long id) {
        Candidato candidato = candidatoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidato não encontrado"));
        return toResponseDTO(candidato);
    }

    public CandidatoResponseDTO salvar(CandidatoRequestDTO dto) {
        if (candidatoRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }
        Candidato candidato = new Candidato();
        candidato.setNomeCompleto(dto.getNomeCompleto());
        candidato.setEmail(dto.getEmail());
        candidato.setSenha(dto.getSenha());
        candidato.setDataCadastro(LocalDateTime.now());
        candidato.setStatusConta("ATIVO");
        candidato.setRua(dto.getRua());
        candidato.setNumero(dto.getNumero());
        candidato.setBairro(dto.getBairro());
        candidato.setComplemento(dto.getComplemento());
        candidato.setCidade(dto.getCidade());
        candidato.setEstado(dto.getEstado());
        candidato.setCep(dto.getCep());
        candidato.setAreaInteresse(dto.getAreaInteresse());

        return toResponseDTO(candidatoRepository.save(candidato));
    }

    public CandidatoResponseDTO atualizar(Long id, CandidatoRequestDTO dto) {
        Candidato candidato = candidatoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidato não encontrado"));
        candidato.setNomeCompleto(dto.getNomeCompleto());
        candidato.setEmail(dto.getEmail());
        candidato.setSenha(dto.getSenha());
        candidato.setRua(dto.getRua());
        candidato.setNumero(dto.getNumero());
        candidato.setBairro(dto.getBairro());
        candidato.setComplemento(dto.getComplemento());
        candidato.setCidade(dto.getCidade());
        candidato.setEstado(dto.getEstado());
        candidato.setCep(dto.getCep());
        candidato.setAreaInteresse(dto.getAreaInteresse());

        return toResponseDTO(candidatoRepository.save(candidato));
    }

    public void deletar(Long id) {
        candidatoRepository.deleteById(id);
    }

    public Candidato login(String email, String senha) {
        return candidatoRepository.findByEmailAndSenha(email, senha).orElse(null);
    }

    private CandidatoResponseDTO toResponseDTO(Candidato candidato) {
        CandidatoResponseDTO dto = new CandidatoResponseDTO();
        dto.setCodCandidato(candidato.getCodCandidato());
        dto.setNomeCompleto(candidato.getNomeCompleto());
        dto.setEmail(candidato.getEmail());
        dto.setDataCadastro(candidato.getDataCadastro());
        dto.setStatusConta(candidato.getStatusConta());
        dto.setRua(candidato.getRua());
        dto.setNumero(candidato.getNumero());
        dto.setBairro(candidato.getBairro());
        dto.setComplemento(candidato.getComplemento());
        dto.setCidade(candidato.getCidade());
        dto.setEstado(candidato.getEstado());
        dto.setCep(candidato.getCep());
        dto.setAreaInteresse(candidato.getAreaInteresse());
        return dto;
    }
}
