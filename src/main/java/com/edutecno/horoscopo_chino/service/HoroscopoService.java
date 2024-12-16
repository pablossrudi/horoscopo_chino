package com.edutecno.horoscopo_chino.service;

import com.edutecno.horoscopo_chino.model.Horoscopo;

import java.sql.Date;

public interface HoroscopoService {
    Horoscopo getHoroscopoByFecha(Date fecha);
}
