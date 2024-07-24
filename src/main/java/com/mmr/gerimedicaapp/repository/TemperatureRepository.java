package com.mmr.gerimedicaapp.repository;

import com.mmr.gerimedicaapp.domain.entity.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemperatureRepository extends JpaRepository<Temperature, String> {
    List<Temperature> findAllByCode(String code);
}
