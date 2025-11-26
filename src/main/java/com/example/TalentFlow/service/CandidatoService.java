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

    public List<CandidatoResponseDTO> listarCandidatos(){
        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public CandidatoResponseDTO buscarCandidato(Long codCandidato) {
        Candidato candidato = repository.findById(codCandidato)
                .orElseThrow(()->new RuntimeException("Candidato não encontrado!"));
        return toResponseDTO(candidato);
    }

    public CandidatoResponseDTO salvarCandidato(CandidatoRequestDTO dto) {
        Candidato candidato = new Candidato();
        candidato.setNomeCompleto(dto.getNomeCompleto());
        candidato.setCpf(dto.getCpf());
        candidato.setEmail(dto.getEmail());
        candidato.setNumeroTelefone(dto.getNumeroTelefone());
        candidato.setAreaInteresse(dto.getAreaInteresse());
        candidato.setFormacaoAcademica(dto.getFormacaoAcademica());
        candidato.setExperienciaProfissional(dto.getExperienciaProfissional());
        candidato.setEnderecoCep(dto.getEnderecoCep());
        candidato.setEnderecoRua(dto.getEnderecoRua());
        candidato.setEnderecoNumero(dto.getEnderecoNumero());
        candidato.setEnderecoComplemento(dto.getEnderecoComplemento());
        candidato.setEnderecoBairro(dto.getEnderecoBairro());
        candidato.setEnderecoCidade(dto.getEnderecoCidade());
        candidato.setEnderecoEstado(dto.getEnderecoEstado());
        candidato.setDataCadastro(dto.getDataCadastro());
        candidato.setStatusConta(dto.getStatusConta());
        Candidato salvo = repository.save(candidato);
        return toResponseDTO(salvo);
    }

    public CandidatoResponseDTO atualizarCandidato(Long codCandidato, CandidatoRequestDTO dto) {
        Candidato candidato = repository.findById(codCandidato)
                .orElseThrow(()-> new RuntimeException("Candidato não encontrado!"));
        candidato.setNomeCompleto(dto.getNomeCompleto());
        candidato.setEmail(dto.getEmail());
        if (dto.getCpf()!= null && !dto.getCpf().isBlank()){
            candidato.setCpf(dto.getCpf());
        }
        candidato.setNumeroTelefone(dto.getNumeroTelefone());
        candidato.setAreaInteresse(dto.getAreaInteresse());
        candidato.setFormacaoAcademica(dto.getFormacaoAcademica());
        candidato.setExperienciaProfissional(dto.getExperienciaProfissional());
        candidato.setEnderecoCep(dto.getEnderecoCep());
        candidato.setEnderecoRua(dto.getEnderecoRua());
        candidato.setEnderecoNumero(dto.getEnderecoNumero());
        candidato.setEnderecoComplemento(dto.getEnderecoComplemento());
        candidato.setEnderecoBairro(dto.getEnderecoBairro());
        candidato.setEnderecoCidade(dto.getEnderecoCidade());
        candidato.setEnderecoEstado(dto.getEnderecoEstado());
        candidato.setDataCadastro(dto.getDataCadastro());
        candidato.setStatusConta(dto.getStatusConta());
        Candidato atualizado = repository.save(candidato);
        return toResponseDTO(atualizado);
    }

    public void deletarCandidato(Long codCandidato) {
        repository.deleteById(codCandidato);
    }

    private CandidatoResponseDTO toResponseDTO(Candidato candidato) {
        CandidatoResponseDTO dto = new CandidatoResponseDTO();
        dto.setCodCandidato(candidato.getCodCandidato());
        candidato.setNomeCompleto(dto.getNomeCompleto());
        candidato.setEmail(dto.getEmail());
        candidato.setNumeroTelefone(dto.getNumeroTelefone());
        candidato.setAreaInteresse(dto.getAreaInteresse());
        candidato.setFormacaoAcademica(dto.getFormacaoAcademica());
        candidato.setExperienciaProfissional(dto.getExperienciaProfissional());
        candidato.setEnderecoCep(dto.getEnderecoCep());
        candidato.setEnderecoRua(dto.getEnderecoRua());
        candidato.setEnderecoNumero(dto.getEnderecoNumero());
        candidato.setEnderecoComplemento(dto.getEnderecoComplemento());
        candidato.setEnderecoBairro(dto.getEnderecoBairro());
        candidato.setEnderecoCidade(dto.getEnderecoCidade());
        candidato.setEnderecoEstado(dto.getEnderecoEstado());
        candidato.setDataCadastro(dto.getDataCadastro());
        candidato.setStatusConta(dto.getStatusConta());
        return dto;
    }
}