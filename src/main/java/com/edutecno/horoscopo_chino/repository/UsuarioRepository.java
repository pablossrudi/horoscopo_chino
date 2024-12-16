package com.edutecno.horoscopo_chino.repository;

import com.edutecno.horoscopo_chino.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {
    boolean save(Usuario usuario);
    Optional<Usuario> findByUsername(String username);
    Optional<Usuario> findByEmail(String email);
    List<Usuario> findAll();
    Usuario findById(int id);
    boolean update(Usuario usuario, int id);
    boolean updatePassword(Usuario usuario, int id);
    boolean delete(int id);
}
