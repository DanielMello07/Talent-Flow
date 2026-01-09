package com.example.TalentFlow.service;

import com.example.TalentFlow.enums.StatusCandidatoVaga;
import com.example.TalentFlow.model.Candidato;
import com.example.TalentFlow.model.CandidatoVaga;
import com.example.TalentFlow.model.CandidatoVagaId;
import com.example.TalentFlow.model.Vaga;
import com.example.TalentFlow.repository.CandidatoRepository;
import com.example.TalentFlow.repository.CandidatoVagaRepository;
import com.example.TalentFlow.repository.VagaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidatoVagaService {

    private final CandidatoRepository candidatoRepository;
    private final VagaRepository vagaRepository;
    private final CandidatoVagaRepository candidatoVagaRepository;

    /**
     * 📌 Método usado quando o candidato se candidata a uma vaga
     * Arquivo: CandidatoVagaService.java
     */
    public CandidatoVaga candidatar(Long codCandidato, Long codVaga) {

        // 1️⃣ Busca o candidato
        Candidato candidato = candidatoRepository.findById(codCandidato)
                .orElseThrow(() -> new RuntimeException("Candidato não encontrado"));

        // 2️⃣ Busca a vaga
        Vaga vaga = vagaRepository.findById(codVaga)
                .orElseThrow(() -> new RuntimeException("Vaga não encontrada"));

        // 3️⃣ Verifica se a vaga está encerrada
        // ⚠️ Usa o campo REAL da entidade Vaga (status String)
        if (!vaga.isAtiva()) {
            throw new RuntimeException("Esta vaga já foi encerrada");
        }
        CandidatoVagaId id = new CandidatoVagaId(
                candidato.getCodCandidato(),
                vaga.getCodVaga()
        );

        // 4️⃣ Impede candidatura duplicada
        if (candidatoVagaRepository.existsByIdCodCandidatoAndIdCodVaga(codCandidato, codVaga)) {
            throw new RuntimeException("Candidato já inscrito nesta vaga");
        }

        // 5️⃣ Cria o relacionamento CandidatoVaga
        CandidatoVaga cv = new CandidatoVaga();
        cv.setId(id);
        cv.setCandidato(candidato);
        cv.setVaga(vaga);
        cv.setStatus(StatusCandidatoVaga.INSCRITO);
        cv.setDataAplicacao(LocalDateTime.now());

        // 6️⃣ Salva no banco
        return candidatoVagaRepository.save(cv);
    }


    public List<CandidatoVaga> listarPorCandidato(Long codCandidato) {

        candidatoRepository.findById(codCandidato)
                .orElseThrow(() -> new RuntimeException("Candidato não encontrado"));

        return candidatoVagaRepository.findByCandidatoCodCandidato(codCandidato);
    }

    public List<CandidatoVaga> listarPorVaga(Long codVaga, Long codEmpresa) {

        // 1️⃣ Busca a vaga
        Vaga vaga = vagaRepository.findById(codVaga)
                .orElseThrow(() -> new RuntimeException("Vaga não encontrada"));

        // 2️⃣ Verifica se a vaga pertence à empresa logada
        if (!vaga.getEmpresa().getCodEmpresa().equals(codEmpresa)) {
            throw new RuntimeException("Acesso negado: vaga não pertence à empresa");
        }

        // 3️⃣ Retorna os candidatos inscritos (ordenados por data)
        return candidatoVagaRepository.findByVagaOrderByDataAplicacaoAsc(codVaga);
    }

    public CandidatoVaga alterarStatus(
            Long codEmpresa,
            Long codCandidato,
            Long codVaga,
            StatusCandidatoVaga novoStatus) {

        // 1️⃣ Busca a candidatura
        CandidatoVagaId id = new CandidatoVagaId(codCandidato, codVaga);

        CandidatoVaga cv = candidatoVagaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidatura não encontrada"));

        // 2️⃣ Segurança mínima: vaga pertence à empresa?
        if (!cv.getVaga().getEmpresa().getCodEmpresa().equals(codEmpresa)) {
            throw new RuntimeException("Acesso negado: vaga não pertence à empresa");
        }

        // 3️⃣ Atualiza status
        cv.setStatus(novoStatus);

        return candidatoVagaRepository.save(cv);
    }


}
