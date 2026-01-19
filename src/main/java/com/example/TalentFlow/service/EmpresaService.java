package com.example.TalentFlow.service;

import com.example.TalentFlow.dto.EmpresaRequestDTO;
import com.example.TalentFlow.dto.EmpresaResponseDTO;
import com.example.TalentFlow.model.Empresa;
import com.example.TalentFlow.model.Vaga;
import com.example.TalentFlow.repository.EmpresaRepository;
import com.example.TalentFlow.repository.VagaRepository;
import com.example.TalentFlow.repository.CandidatoVagaRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final VagaRepository vagaRepository;
    private final CandidatoVagaRepository candidatoVagaRepository;

    public EmpresaService(EmpresaRepository empresaRepository, CandidatoVagaRepository candidatoVagaRepository, VagaRepository vagaRepository) {
        this.empresaRepository = empresaRepository;
        this.vagaRepository = vagaRepository;
        this.candidatoVagaRepository = candidatoVagaRepository;
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
        empresa.setSenha(dto.getSenha());
        empresa.setEmailCorporativo(dto.getEmailCorporativo());
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

    public Optional<Empresa> realizarLogin(String email, String senha) {
        return empresaRepository.findByEmailCorporativo(email)
                .filter(empresa -> empresa.getSenha().equals(senha));
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

    public long VagasAtivas(long codEmpresa){
        List<Vaga> vagas = vagaRepository.findByAtivaTrue();

        return vagas.stream()
                .filter(vaga -> vaga.getEmpresa().getCodEmpresa() == codEmpresa)
                .count();

    }

    public long CandidaturasVagasAtivas(long codEmpresa){
        List<Vaga> vagas = vagaRepository.findByAtivaTrue();

        return vagas.stream()
                .filter(vaga -> vaga.getEmpresa().getCodEmpresa() == codEmpresa)
                .mapToLong(vaga -> candidatoVagaRepository.candidaturasVagasAtivas(vaga.getCodVaga()))
                .sum();

    }

}
