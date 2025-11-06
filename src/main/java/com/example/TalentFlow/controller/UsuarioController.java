package com.example.TalentFlow.controller;

import com.example.TalentFlow.model.Usuario;
import com.example.TalentFlow.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioRepository repository;

    public UsuarioController(UsuarioRepository repository){
        this.repository=repository;
    }

    @GetMapping  // define o método GET HTTP
    public List<Usuario>listar(){
        return repository.findAll();
    }

    @GetMapping("/{codUsuario}")  // define o método GET HTTP com parametro
    public Usuario buscar(@PathVariable Long codUsuario){
        return repository.findById(codUsuario)
                .orElseThrow(()-> new RuntimeException("Usuario não encontrado!"));
    }

    @PostMapping // define metodo POST HTTP
    public Usuario salvar(@RequestBody Usuario usuario){
        return repository.save(usuario);
    }

    @PutMapping("/{codUsuario}")  // define o método PUT  HTTP com parametro
    public Usuario atualizar(@PathVariable Long codUsuario, @RequestBody Usuario dados){
        Usuario usuario=repository.findById(codUsuario)
                .orElseThrow(()->new RuntimeException("Usuario não encontrado!"));
        usuario.setNomeCompleto(dados.getNomeCompleto());
        usuario.setEmail(dados.getEmail());
        usuario.setSenha(dados.getSenha());
        usuario.setTipoUsuario(dados.getTipoUsuario());
        usuario.setDataCadastro(dados.getDataCadastro());
        usuario.setStatusConta(dados.getStatusConta());
        return repository.save(usuario);
    }

    @DeleteMapping("/{codUsuario}") // define o método DELETE HTTP com parametro
    public void deletar(@PathVariable Long codUsuario){
        repository.deleteById(codUsuario);
    }
}