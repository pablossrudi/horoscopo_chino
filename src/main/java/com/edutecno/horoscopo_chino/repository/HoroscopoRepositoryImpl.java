package com.edutecno.horoscopo_chino.repository;

import com.edutecno.horoscopo_chino.configuration.DatabaseConfig;
import com.edutecno.horoscopo_chino.model.Horoscopo;

import java.sql.*;
import java.util.Optional;

public class HoroscopoRepositoryImpl implements HoroscopoRepository {
    private final DatabaseConfig databaseConfig;

    public HoroscopoRepositoryImpl() {
        this.databaseConfig = DatabaseConfig.getInstance();
    }

    @Override
    public Optional<Horoscopo> findByfecha(Date fecha) {
        String sql = "SELECT animal, fecha_inicio, fecha_fin FROM horoscopo WHERE ? between fecha_inicio and fecha_fin";
        try (Connection conn = databaseConfig.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setDate(1, fecha);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(new Horoscopo(
                        resultSet.getString("animal"),
                        resultSet.getDate("fecha_inicio"),
                        resultSet.getDate("fecha_fin")
                ));
            }
            return Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
