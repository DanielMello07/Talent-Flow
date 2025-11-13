package com.example.TalentFlow.controller;

import com.example.TalentFlow.dto.UsuarioRequestDTO;
import com.example.TalentFlow.dto.UsuarioResponseDTO;
import com.example.TalentFlow.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service){
        this.service=service;
    }

    @GetMapping  // define o método GET HTTP
    public List<UsuarioResponseDTO>listar(){
        return service.listar();
    }

    @GetMapping("/{codUsuario}")  // define o método GET HTTP com parametro
    public UsuarioResponseDTO buscar(@PathVariable Long codUsuario){
        return service.buscar(codUsuario);
    }

    @PostMapping // define metodo POST HTTP
    public UsuarioResponseDTO salvar(@RequestBody UsuarioRequestDTO dto){
        return service.salvar(dto);
    }

    @PutMapping("/{codUsuario}")
    public UsuarioResponseDTO atualizar(@PathVariable Long codUsuario, @RequestBody UsuarioRequestDTO dto){
        return service.atualizar(codUsuario, dto);
    }

    @DeleteMapping("/{codUsuario}")
    public void deletar(@PathVariable Long codUsuario){
        service.deletar(codUsuario);
    }
}