package com.example.TalentFlow.service;

import com.example.TalentFlow.dto.EmpresaResponseDTO;
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
                .filter(vaga -> vaga.isAtiva())
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
        vaga.setAtiva(true);
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

        // Mapeamento do DTO aninhado
        if (vaga.getEmpresa() != null) {
            EmpresaResponseDTO empresaDTO = new EmpresaResponseDTO();
            empresaDTO.setCodEmpresa(vaga.getEmpresa().getCodEmpresa());
            empresaDTO.setNome(vaga.getEmpresa().getNome());
            empresaDTO.setCnpj(vaga.getEmpresa().getCnpj());
            empresaDTO.setDescricao(vaga.getEmpresa().getDescricao());
            empresaDTO.setContatoRecrutador(vaga.getEmpresa().getContatoRecrutador());
            empresaDTO.setEmailCorporativo(vaga.getEmpresa().getEmailCorporativo());

            dto.setEmpresa(empresaDTO); // Adiciona o DTO da empresa dentro do DTO da vaga
        }

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

    public List<VagaResponseDTO> filtrarVagas(String localizacao, String empresaNome, String textoBusca) {
        // Exemplo simplificado com Stream (pode ser migrado para JPA Specification para performance em grandes bancos)
        return vagaRepository.findAll().stream()
                .filter(v -> v.isAtiva()) // Apenas vagas ativas
                .filter(v -> textoBusca == null || v.getTitulo().toLowerCase().contains(textoBusca.toLowerCase())
                        || v.getDescricao().toLowerCase().contains(textoBusca.toLowerCase()))
                .filter(v -> localizacao == null || v.getArea().toLowerCase().contains(localizacao.toLowerCase())) // Assumindo 'Area' como local
                .filter(v -> empresaNome == null || v.getEmpresa().getNome().equalsIgnoreCase(empresaNome))
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public Vaga encerrarVaga(Long codEmpresa, Long codVaga) {

        // 1️⃣ Busca a vaga
        Vaga vaga = vagaRepository.findById(codVaga)
                .orElseThrow(() -> new RuntimeException("Vaga não encontrada"));

        // 2️⃣ Segurança mínima: vaga pertence à empresa?
        if (!vaga.getEmpresa().getCodEmpresa().equals(codEmpresa)) {
            throw new RuntimeException("Acesso negado: vaga não pertence à empresa");
        }

        // 3️⃣ Já está encerrada?
        if (!vaga.isAtiva()) {
            throw new RuntimeException("Vaga já está encerrada");
        }

        // 4️⃣ Encerra a vaga
        vaga.setAtiva(false);

        return vagaRepository.save(vaga);
    }


}
