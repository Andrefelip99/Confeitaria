package com.confeitaria.confeitaria.service;

import com.confeitaria.confeitaria.model.Usuario;
import com.confeitaria.confeitaria.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario atualizar(Integer id, Usuario novoUsuario) {
        return usuarioRepository.findById(id).map(u -> {
            u.setUsername(novoUsuario.getUsername());
            u.setSenha(novoUsuario.getSenha());
            u.setRole(novoUsuario.getRole());
            return usuarioRepository.save(u);
        }).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public void deletar(Integer id) {
        usuarioRepository.deleteById(id);
    }
    public boolean existePorId(Integer id) {
    return usuarioRepository.existsById(id);
}



    
}
