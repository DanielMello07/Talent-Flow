package com.example.TalentFlow.service;

import com.example.TalentFlow.dto.VagaRequestDTO;
import com.example.TalentFlow.dto.VagaResponseDTO;
import com.example.TalentFlow.model.Empresa;
import com.example.TalentFlow.model.Vaga;
import com.example.TalentFlow.repository.EmpresaRepository;
import com.example.TalentFlow.repository.VagaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VagaService {

    private final VagaRepository vagaRepository;
    private final EmpresaRepository empresaRepository;

    public VagaService(VagaRepository vagaRepository, EmpresaRepository empresaRepository) {
        this.vagaRepository = vagaRepository;
        this.empresaRepository = empresaRepository;
    }

    public List<VagaResponseDTO> listar() {
        return vagaRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public VagaResponseDTO buscar(Long id) {
        Vaga vaga = vagaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vaga não encontrada"));
        return toResponseDTO(vaga);
    }

    public VagaResponseDTO salvar(VagaRequestDTO dto) {
        Empresa empresa = empresaRepository.findById(dto.getCodEmpresa())
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
        Vaga vaga = new Vaga();
        vaga.setTitulo(dto.getTitulo());
        vaga.setDescricao(dto.getDescricao());
        vaga.setArea(dto.getArea());
        vaga.setEmpresa(empresa);
        return toResponseDTO(vagaRepository.save(vaga));
    }

    public VagaResponseDTO atualizar(Long id, VagaRequestDTO dto) {
        Vaga vaga = vagaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vaga não encontrada"));
        Empresa empresa = empresaRepository.findById(dto.getCodEmpresa())
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
        vaga.setTitulo(dto.getTitulo());
        vaga.setDescricao(dto.getDescricao());
        vaga.setArea(dto.getArea());
        vaga.setEmpresa(empresa);
        return toResponseDTO(vagaRepository.save(vaga));
    }

    public void deletar(Long id) {
        vagaRepository.deleteById(id);
    }

    private VagaResponseDTO toResponseDTO(Vaga vaga) {
        VagaResponseDTO dto = new VagaResponseDTO();
        dto.setCodVaga(vaga.getCodVaga());
        dto.setTitulo(vaga.getTitulo());
        dto.setDescricao(vaga.getDescricao());
        dto.setArea(vaga.getArea());
        dto.setCodEmpresa(vaga.getEmpresa() != null ? vaga.getEmpresa().getCodEmpresa() : null);
        return dto;
    }

    public List<Vaga> buscarVagasPorArea(String area) {
        return vagaRepository.findByAreaIgnoreCase(area);
    }

    public List<Vaga> buscarVagasPorEmpresa(Long codEmpresa) {
        return vagaRepository.findByEmpresaCodEmpresa(codEmpresa);
    }

    public List<Vaga> buscarVagasAtivas() {
        return vagaRepository.findByAtivaTrue();
    }
}
