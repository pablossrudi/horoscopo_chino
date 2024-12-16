package com.edutecno.horoscopo_chino.repository;

import com.edutecno.horoscopo_chino.configuration.DatabaseConfig;
import com.edutecno.horoscopo_chino.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final DatabaseConfig databaseConfig;

    public UsuarioRepositoryImpl() {
        this.databaseConfig = DatabaseConfig.getInstance();
    }

    @Override
    public boolean save(Usuario usuario) {
        String sql = "INSERT INTO usuario (nombre, username, email, fecha_nacimiento, password, animal) VALUES (?,?,?,?,?,?)";
        try (Connection conn = databaseConfig.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)){
                statement.setString(1, usuario.getNombre());
                statement.setString(2, usuario.getUsername());
                statement.setString(3, usuario.getEmail());
                statement.setDate(4, usuario.getFechaNacimiento());
                statement.setString(5, usuario.getPassword());
                statement.setString(6, usuario.getAnimal());

                return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Optional<Usuario> findByUsername(String username) {
        String sql = "SELECT id, nombre, username, email, fecha_nacimiento, password, animal FROM usuario WHERE username = ?";
        try (Connection conn = databaseConfig.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return Optional.of(new Usuario(
                   rs.getInt("id"),
                   rs.getString("nombre"),
                   rs.getString("username") ,
                   rs.getString("email"),
                   rs.getDate("fecha_nacimiento"),
                   rs.getString("password"),
                   rs.getString("animal")
                ));
            }
            return Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        String sql = "SELECT id, nombre, username, email, fecha_nacimiento, password, animal FROM usuario WHERE email = ?";
        try (Connection conn = databaseConfig.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return Optional.of(new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getDate("fecha_nacimiento"),
                        rs.getString("password"),
                        rs.getString("animal")
                ));
            }
            return Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public List<Usuario> findAll() {
        String sql = "SELECT nombre, username, email, fecha_nacimiento, password, animal FROM usuario";
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection conn = databaseConfig.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)){
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                usuarios.add(new Usuario(
                        rs.getString("nombre"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getDate("fecha_nacimiento"),
                        rs.getString("password"),
                        rs.getString("animal")
                ));
            }
            return usuarios;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public Usuario findById(int id) {
        String sql = "SELECT id, nombre, username, email, fecha_nacimiento, password, animal FROM usuario WHERE id = ?";
        try (Connection conn = databaseConfig.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getDate("fecha_nacimiento"),
                        rs.getString("password"),
                        rs.getString("animal")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Usuario usuario, int id) {
        String sql = "UPDATE usuario SET "
                + (usuario.getNombre() != null ? "nombre = ?, " : "")
                + (usuario.getUsername() != null ? "username = ?, " : "")
                + (usuario.getEmail() != null ? "email = ?, " : "")
                + (usuario.getFechaNacimiento() != null ? "fecha_nacimiento = ?, " : "")
                + (usuario.getAnimal() != null ? "animal = ? " : "")
                + "WHERE id = ?";
        try (Connection conn = databaseConfig.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            int index = 1;

            if (usuario.getNombre() != null) {
                statement.setString(index++, usuario.getNombre());
            }
            if (usuario.getUsername() != null) {
                statement.setString(index++, usuario.getUsername());
            }
            if (usuario.getEmail() != null) {
                statement.setString(index++, usuario.getEmail());
            }
            if (usuario.getFechaNacimiento() != null) {
                statement.setDate(index++, usuario.getFechaNacimiento());
            }
            if (usuario.getAnimal() != null) {
                statement.setString(index++, usuario.getAnimal());
            }

            statement.setInt(index, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updatePassword(Usuario usuario, int id) {
        String sql = "UPDATE usuario SET password = ? WHERE id = ?";
        try (Connection conn = databaseConfig.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, usuario.getPassword());
            statement.setInt(2, id);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM usuario WHERE id = ?";
        try (Connection conn = databaseConfig.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
