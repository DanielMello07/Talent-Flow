package com.example.TalentFlow.service;

import com.example.TalentFlow.dto.EmpresaRequestDTO;
import com.example.TalentFlow.dto.EmpresaResponseDTO;
import com.example.TalentFlow.model.Empresa;
import com.example.TalentFlow.repository.EmpresaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public List<EmpresaResponseDTO> listar() {
        return empresaRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public EmpresaResponseDTO buscar(Long id) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
        return toResponseDTO(empresa);
    }

    public EmpresaResponseDTO salvar(EmpresaRequestDTO dto) {
        Empresa empresa = new Empresa();
        empresa.setNome(dto.getNome());
        empresa.setCnpj(dto.getCnpj());
        empresa.setDescricao(dto.getDescricao());
        empresa.setContatoRecrutador(dto.getContatoRecrutador());
        return toResponseDTO(empresaRepository.save(empresa));
    }

    public EmpresaResponseDTO atualizar(Long id, EmpresaRequestDTO dto) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
        empresa.setNome(dto.getNome());
        empresa.setCnpj(dto.getCnpj());
        empresa.setDescricao(dto.getDescricao());
        empresa.setContatoRecrutador(dto.getContatoRecrutador());
        return toResponseDTO(empresaRepository.save(empresa));
    }

    public void deletar(Long id) {
        empresaRepository.deleteById(id);
    }

    public Empresa login(String nome, String cnpj) {
        return empresaRepository.findByNomeAndCnpj(nome, cnpj).orElse(null);
    }

    private EmpresaResponseDTO toResponseDTO(Empresa e) {
        EmpresaResponseDTO dto = new EmpresaResponseDTO();
        dto.setCodEmpresa(e.getCodEmpresa());
        dto.setNome(e.getNome());
        dto.setCnpj(e.getCnpj());
        dto.setDescricao(e.getDescricao());
        dto.setContatoRecrutador(e.getContatoRecrutador());
        return dto;
    }
}
