package com.edutecno.horoscopo_chino.repository;

import com.edutecno.horoscopo_chino.model.Horoscopo;

import java.sql.Date;
import java.util.Optional;

public interface HoroscopoRepository {
    Optional<Horoscopo> findByfecha(Date fecha);
}
