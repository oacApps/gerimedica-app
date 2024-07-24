package com.mmr.gerimedicaapp.service;

import com.mmr.gerimedicaapp.domain.entity.Temperature;
import com.mmr.gerimedicaapp.repository.TemperatureRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemperatureService {

    private final TemperatureRepository repository;

    public TemperatureService(TemperatureRepository repository) {
        this.repository = repository;
    }

    public void saveAll(List<Temperature> records) {
        repository.saveAll(records);
    }

    public List<Temperature> getAll() {
        return repository.findAll();
    }

    public List<Temperature> getByCode(String code) {
        return repository.findAllByCode(code);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
