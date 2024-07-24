package com.mmr.gerimedicaapp.controller;

import com.mmr.gerimedicaapp.domain.mapper.TemperatureMapper;
import com.mmr.gerimedicaapp.domain.model.Temperature;
import com.mmr.gerimedicaapp.service.TemperatureService;
import com.mmr.gerimedicaapp.utils.CsvFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/temperature")
public class TemperatureController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final TemperatureService temperatureService;
    private final CsvFileReader csvFileReader;

    public TemperatureController(TemperatureService temperatureService, CsvFileReader csvFileReader) {
        this.temperatureService = temperatureService;
        this.csvFileReader = csvFileReader;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty");
        }
        try {
            List<Temperature> temperatures = csvFileReader.readCsvFile(file);
            temperatureService.saveAll(TemperatureMapper.INSTANCE.toEntityList(temperatures));
            return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully");
        } catch (IOException e) {
            log.error("Error reading file {}", e.getMessage());
        }

        return null;
    }

    @GetMapping
    public List<Temperature> getAll() {
        return TemperatureMapper.INSTANCE.toModelList(temperatureService.getAll());
    }

    @GetMapping("/{code}")
    public List<Temperature> getByCode(@PathVariable String code) {
        return TemperatureMapper.INSTANCE.toModelList(temperatureService.getByCode(code));
    }

    @GetMapping("/delete")
    public ResponseEntity<String> deleteAll() {
        temperatureService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("All data deleted successfully");
    }
}
