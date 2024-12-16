package com.edutecno.horoscopo_chino.configuration;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    static Dotenv dotenv = Dotenv.load();
    private static final String DRIVER = dotenv.get("DRIVER_DB");
    private static final String URL = dotenv.get("URL_DB");
    private static final String USER = dotenv.get("USER_DB");
    private static final String PASS = dotenv.get("PASS_DB");

    private static DatabaseConfig instance;
    private Connection connection;

    private DatabaseConfig() {
        try {
            Class.forName(DRIVER);
            this.connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized DatabaseConfig getInstance() {
        if (instance == null) {
            instance = new DatabaseConfig();
        }
        return instance;
    }
    public Connection getConnection() {
        try {
            if (this.connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASS);
            }
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener conexion de la base de datos ",e);
        }
    }

    public void closeConnection() {
        try {
            if (this.connection != null || !this.connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al cerrar conexion de la base de datos ", e);
        }
    }
}
