package com.example.TalentFlow.controller;

// apenas para teste (não para produção)


import com.example.TalentFlow.model.EnderecoViaCep;
import com.example.TalentFlow.service.EnderecoViaCepService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enderecos")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EnderecoViaCepController {

    private final EnderecoViaCepService enderecoViaCepService;

    @GetMapping("/{cep}")
    public EnderecoViaCep getEndereco(@PathVariable String cep) {
        return enderecoViaCepService.buscarEndereco(cep);
    }
}
