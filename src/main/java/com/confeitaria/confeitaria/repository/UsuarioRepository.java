package com.confeitaria.confeitaria.repository;

import com.confeitaria.confeitaria.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
