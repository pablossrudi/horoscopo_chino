package com.edutecno.horoscopo_chino.dto;

import java.sql.Date;

public class UsuarioCreateDTO {
    private String nombre;
    private String username;
    private String email;
    private Date fechaNacimiento;
    private String password;
    private String animal;

    public UsuarioCreateDTO() {
    }

    public UsuarioCreateDTO(String nombre, String username, String email, Date fechaNacimiento, String animal) {
        this.nombre = nombre;
        this.username = username;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.animal = animal;
    }

    public UsuarioCreateDTO(String nombre, String username, String email, Date fechaNacimiento, String password, String animal) {
        this.nombre = nombre;
        this.username = username;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.password = password;
        this.animal = animal;
    }

    public UsuarioCreateDTO(String password) {
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

}