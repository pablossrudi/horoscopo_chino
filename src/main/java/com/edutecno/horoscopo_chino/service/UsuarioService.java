package com.edutecno.horoscopo_chino.service;

import com.edutecno.horoscopo_chino.dto.UsuarioCreateDTO;
import com.edutecno.horoscopo_chino.dto.UsuarioResponseDTO;

import java.util.List;

public interface UsuarioService {
    boolean registrarUsuario(UsuarioCreateDTO usuario, String confirmPassword);
    boolean autenticarUsuario(String username, String password);
    UsuarioResponseDTO buscarPorUsername(String username);
    List<UsuarioResponseDTO> findAll();
    boolean actualizarUsuario(UsuarioCreateDTO usuario,UsuarioResponseDTO user);
    boolean actualizarContrasena(UsuarioCreateDTO usuario,UsuarioResponseDTO user, String confirmPassword, String currentPassword);
    boolean eliminarUsuario(int id);
}
