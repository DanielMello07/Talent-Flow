package com.example.TalentFlow.service;

import com.example.TalentFlow.dto.UsuarioRequestDTO;
import com.example.TalentFlow.dto.UsuarioResponseDTO;
import com.example.TalentFlow.model.Usuario;
import com.example.TalentFlow.repository.UsuarioRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository){
        this.repository = repository;
    }

    public List<UsuarioResponseDTO> listar(){
        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public UsuarioResponseDTO buscar(Long codUsuario) {
        Usuario usuario = repository.findById(codUsuario)
                .orElseThrow(()->new RuntimeException("Usuário não encontrado"));
        return toResponseDTO(usuario);
    }

    public UsuarioResponseDTO salvar(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNomeCompleto(dto.getNomeCompleto());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setTipoUsuario(dto.getTipoUsuario());
        usuario.setDataCadastro(dto.getDataCadastro());
        usuario.setStatusConta(dto.getStatusConta());
        Usuario salvo = repository.save(usuario);
        return toResponseDTO(salvo);
    }

    public UsuarioResponseDTO atualizar(Long codUsuario, UsuarioRequestDTO dto) {
        Usuario usuario = repository.findById(codUsuario)
                .orElseThrow(()->) new RuntimeException("Usuario não encontrado");
        usuario.setNomeCompleto(dto.getNomeCompleto());
        usuario.setEmail(dto.getEmail());
        if (dto.getSenha()!= null && !dto.getSenha().isBlank()){
            usuario.setSenha(dto.getSenha());
        }
        usuario.setTipoUsuario(dto.getTipoUsuario());
        usuario.setDataCadastro(dto.getDataCadastro());
        usuario.setStatusConta(dto.getStatusConta());
        Usuario atualizado = repository.save(usuario);
        return toResponseDTO(atualizado);
    }

    public void deletar(Long codUsuario) {
        repository.deleteById(codUsuario);
    }

    private UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setCodUsuario(usuario.getCodUsuario());
        dto.setNomeCompleto(usuario.getNomeCompleto());
        dto.setEmail(usuario.getEmail());
        dto.setTipoUsuario(usuario.getTipoUsuario());
        dto.setDataCadastro(usuario.getDataCadastro());
        dto.setStatusConta(usuario.getStatusConta());
        return dto;
    }
}