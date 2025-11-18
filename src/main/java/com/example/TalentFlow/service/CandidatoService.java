package com.example.TalentFlow.service;

import com.example.TalentFlow.dto.CandidatoRequestDTO;
import com.example.TalentFlow.dto.CandidatoResponseDTO;
import com.example.TalentFlow.model.Candidato;
import com.example.TalentFlow.repository.CandidatoRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidatoService {
    private final CandidatoRepository repository;

    public CandidatoService(CandidatoRepository repository){
        this.repository = repository;
    }

    public List<CandidatoResponseDTO> listar(){
        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public CandidatoResponseDTO buscar(Long codCandidato) {
        Candidato candidato = repository.findById(codCandidato)
                .orElseThrow(()->new RuntimeException("Candidato não encontrado"));
        return toResponseDTO(candidato);
    }

    public CandidatoResponseDTO salvar(CandidatoRequestDTO dto) {
        Candidato candidato = new Candidato();
        candidato.setNomeCompleto(dto.getNomeCompleto());
        candidato.setEmail(dto.getEmail());
        candidato.setSenha(dto.getSenha());
        candidato.setDataCadastro(dto.getDataCadastro());
        candidato.setStatusConta(dto.getStatusConta());
        Candidato salvo = repository.save(candidato);
        return toResponseDTO(salvo);
    }

    public CandidatoResponseDTO atualizar(Long codCandidato, CandidatoRequestDTO dto) {
        Candidato candidato = repository.findById(codCandidato)
                .orElseThrow(()-> new RuntimeException("Candidato não encontrado"));
        candidato.setNomeCompleto(dto.getNomeCompleto());
        candidato.setEmail(dto.getEmail());
        if (dto.getSenha()!= null && !dto.getSenha().isBlank()){
            candidato.setSenha(dto.getSenha());
        }
        candidato.setDataCadastro(dto.getDataCadastro());
        candidato.setStatusConta(dto.getStatusConta());
        Candidato atualizado = repository.save(candidato);
        return toResponseDTO(atualizado);
    }

    public void deletar(Long codCandidato) {
        repository.deleteById(codCandidato);
    }

    private CandidatoResponseDTO toResponseDTO(Candidato candidato) {
        CandidatoResponseDTO dto = new CandidatoResponseDTO();
        dto.setCodCandidato(candidato.getCodCandidato());
        dto.setNomeCompleto(candidato.getNomeCompleto());
        dto.setEmail(candidato.getEmail());
        dto.setDataCadastro(candidato.getDataCadastro());
        dto.setStatusConta(candidato.getStatusConta());
        return dto;
    }
}