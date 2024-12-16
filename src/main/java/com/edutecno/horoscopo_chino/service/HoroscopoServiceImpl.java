package com.edutecno.horoscopo_chino.service;

import com.edutecno.horoscopo_chino.model.Horoscopo;
import com.edutecno.horoscopo_chino.repository.HoroscopoRepository;
import com.edutecno.horoscopo_chino.repository.HoroscopoRepositoryImpl;

import java.sql.Date;

public class HoroscopoServiceImpl implements HoroscopoService {

    private final HoroscopoRepository horoscopoRepository;

    public HoroscopoServiceImpl() {
        this.horoscopoRepository = new HoroscopoRepositoryImpl();
    }

    @Override
    public Horoscopo getHoroscopoByFecha(Date fecha) {
        return horoscopoRepository.findByfecha(fecha).orElse(null);
    }
}
