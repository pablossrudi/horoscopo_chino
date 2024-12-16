package com.edutecno.horoscopo_chino.service;
import com.edutecno.horoscopo_chino.dto.UsuarioCreateDTO;
import com.edutecno.horoscopo_chino.dto.UsuarioResponseDTO;
import com.edutecno.horoscopo_chino.mapper.UsuarioMapper;
import com.edutecno.horoscopo_chino.repository.UsuarioRepository;
import com.edutecno.horoscopo_chino.repository.UsuarioRepositoryImpl;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioRepository usuarioRepository;
    private final HoroscopoService horoscopoService;

    public UsuarioServiceImpl() {
        this.usuarioRepository = new UsuarioRepositoryImpl();
        this.horoscopoService = new HoroscopoServiceImpl();
    }

    @Override
    public boolean registrarUsuario(UsuarioCreateDTO usuario, String confirmPassword) {
        // validaciones
        if (!validarDatosRegistro(usuario, confirmPassword)){
            return false;
        }

        // verificar si el username ya existe
        if (usuarioRepository.findByUsername(usuario.getUsername()).isPresent()){
            return false;
        }
        // verificar si el email ya esta registrado
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            return false;
        }

        // ingresar el animal segun la fecha de nacimiento
        usuario.setAnimal(horoscopoService.getHoroscopoByFecha(usuario.getFechaNacimiento()).getAnimal());

        return usuarioRepository.save(UsuarioMapper.toEntity(usuario));
    }

    @Override
    public boolean autenticarUsuario(String username, String password) {
        return usuarioRepository.findByUsername(username)
                .map(usuario -> usuario.getPassword().equals(password))
                .orElse(false);
    }

    @Override
    public UsuarioResponseDTO buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username)
                .map(UsuarioMapper::toDTO).orElse(null);
    }

    @Override
    public List<UsuarioResponseDTO> findAll() {
        return usuarioRepository.findAll().stream().map(UsuarioMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public boolean actualizarUsuario(UsuarioCreateDTO usuario, UsuarioResponseDTO user) {
        // verificar si el username ya existe
        if (!Objects.equals(user.getUsername(), usuario.getUsername())){
            if (usuarioRepository.findByUsername(usuario.getUsername()).isPresent()){
                return false;
            }
        }
        // verificar si el email ya existe
        if (!Objects.equals(user.getEmail(), usuario.getEmail())){
            if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()){
                return false;
            }
        }
        //validaciones
        if (!validarDatosEditados(usuario)){
            return false;
        }
        // ingresar el animal segun la fecha de nacimiento
        usuario.setAnimal(horoscopoService.getHoroscopoByFecha(usuario.getFechaNacimiento()).getAnimal());
        return usuarioRepository.update(UsuarioMapper.toEntity(usuario), user.getId());
    }

    @Override
    public boolean actualizarContrasena(UsuarioCreateDTO usuario, UsuarioResponseDTO user, String confirmPassword, String currentPassword) {

        String usua = usuarioRepository.findById(user.getId()).getPassword();
        //validar la contraseña actual
        if (!Objects.equals(usua, currentPassword)){
            return false;
        }
        //validaciones
        if (!validarDatosContrasena(usuario, confirmPassword)){
           return false;
        }
        return usuarioRepository.updatePassword(UsuarioMapper.toPass(usuario), user.getId());
    }

    @Override
    public boolean eliminarUsuario(int id) {
        return usuarioRepository.delete(id);
    }

    private boolean validarDatosContrasena(UsuarioCreateDTO usuario, String confirmPassword) {
        if (usuario.getPassword().trim().isEmpty() || usuario.getPassword() == null){
            return false;
        }
        return usuario.getPassword().equals(confirmPassword);
    }

    private boolean validarDatosEditados(UsuarioCreateDTO usuario){
        if (usuario.getUsername() == null || usuario.getUsername().trim().isEmpty()){
            return false;
        }
        if (usuario.getPassword() != null ){
            return false;
        }
        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()){
            return false;
        }
        if (usuario.getNombre() == null || usuario.getNombre().trim().isEmpty()){
            return false;
        }

        Date fechaActual = new Date();
        if (usuario.getFechaNacimiento().after(fechaActual)){
            return false;
        }

        Date fechaLimite = new Date(1924 - 1900, 2 - 1, 5);
        if (usuario.getFechaNacimiento().before(fechaLimite)){
            return false;
        }
        return true;
    }

    private boolean validarDatosRegistro(UsuarioCreateDTO usuario, String confirmPassword){

        if (usuario.getUsername() == null || usuario.getUsername().trim().isEmpty()){
            return false;
        }
        if (usuario.getPassword() == null || usuario.getPassword().trim().isEmpty()){
            return false;
        }
        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()){
            return false;
        }
        if (usuario.getNombre() == null || usuario.getNombre().trim().isEmpty()){
            return false;
        }

        if (!usuario.getPassword().equals(confirmPassword)){
            return false;
        }

        Date fechaActual = new Date();
        if (usuario.getFechaNacimiento().after(fechaActual)){
            return false;
        }

        Date fechaLimite = new Date(1924 - 1900, 2 - 1, 5);
        if (usuario.getFechaNacimiento().before(fechaLimite)){
            return false;
        }

        return true;
    }
}
