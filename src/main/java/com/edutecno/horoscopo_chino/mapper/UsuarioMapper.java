package com.edutecno.horoscopo_chino.mapper;

import com.edutecno.horoscopo_chino.dto.UsuarioCreateDTO;
import com.edutecno.horoscopo_chino.dto.UsuarioResponseDTO;
import com.edutecno.horoscopo_chino.model.Usuario;

public class UsuarioMapper {
    public static UsuarioResponseDTO toDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getUsername(),
                usuario.getEmail(),
                usuario.getFechaNacimiento(),
                usuario.getAnimal()
        );
    }

    public static Usuario toEntity(UsuarioCreateDTO dto) {

        return new Usuario(
                dto.getNombre(),
                dto.getUsername(),
                dto.getEmail(),
                dto.getFechaNacimiento(),
                dto.getPassword(),
                dto.getAnimal()
        );
    }

    public static Usuario toPass(UsuarioCreateDTO dto) {
        return new Usuario(
                dto.getPassword()
        );
    }

}
