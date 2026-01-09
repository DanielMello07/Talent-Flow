package com.example.TalentFlow.client;

import com.example.TalentFlow.dto.ViaCepDTO;
import com.example.TalentFlow.model.EnderecoViaCep;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor   // Gera construtor com os atributos "final"
public class ViaCepClient {

    private final RestTemplate restTemplate;

    public ViaCepDTO buscarEndereco(String cep) {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        return restTemplate.getForObject(url,ViaCepDTO.class);
    }
}

