package com.example.TalentFlow.service;

import com.example.TalentFlow.client.ViaCepClient;
import com.example.TalentFlow.dto.ViaCepDTO;
import com.example.TalentFlow.model.EnderecoViaCep;
import com.example.TalentFlow.repository.EnderecoViaCepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnderecoViaCepService {

    private final ViaCepClient viaCepClient;
    private final EnderecoViaCepRepository enderecoViaCepRepository;

    public EnderecoViaCep buscarEndereco(String cep) {
        ViaCepDTO dto = viaCepClient.buscarEndereco(cep);

        // Converte DTO para entidade
        EnderecoViaCep endereco = new EnderecoViaCep(
                dto.getCep(),
                dto.getLogradouro(),
                dto.getBairro(),
                dto.getLocalidade(),
                dto.getUf()
        );

        enderecoViaCepRepository.save(endereco);
        return endereco;

    }
}

