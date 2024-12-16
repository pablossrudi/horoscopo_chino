package com.edutecno.horoscopo_chino.dto;

import java.sql.Date;

public class UsuarioResponseDTO {
    private int id;
    private String nombre;
    private String username;
    private String email;
    private Date fechaNacimiento;
    private String animal;

    public UsuarioResponseDTO() {
    }

    public UsuarioResponseDTO(int id,String nombre, String username, String email, Date fechaNacimiento, String animal) {
        this.id = id;
        this.nombre = nombre;
        this.username = username;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.animal = animal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

}
