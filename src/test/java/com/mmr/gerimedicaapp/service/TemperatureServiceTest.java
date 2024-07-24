package com.mmr.gerimedicaapp.service;

import com.mmr.gerimedicaapp.domain.entity.Temperature;
import com.mmr.gerimedicaapp.repository.TemperatureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TemperatureServiceTest {
    @Mock
    private TemperatureRepository repository;

    @InjectMocks
    private TemperatureService service;

    Temperature temperature;

    List<Temperature> records;

    @BeforeEach
    public void setUp() {
        temperature = new Temperature();
        temperature.setCode("code1");
        temperature.setSource("source1");
        temperature.setCodeListCode("codeListCode1");
        temperature.setDisplayValue("displayValue1");
        temperature.setFromDate("fromDate1");
        temperature.setToDate("toDate1");
        temperature.setSortingPriority("sortingPriority1");
        records = Arrays.asList(temperature);

    }

    @Test
    public void testSaveAll() {
        service.saveAll(records);
        verify(repository).saveAll(records);
    }
    @Test
    public void testGetAll() {
        when(repository.findAll()).thenReturn(records);

        List<Temperature> result = service.getAll();
        assertEquals(records, result);
    }

    @Test
    public void testGetByCode() {
        when(repository.findAllByCode("code1")).thenReturn(records);

        List<Temperature> result = service.getByCode("code1");
        assertEquals(records, result);
    }

    @Test
    public void testDeleteAll() {
        service.deleteAll();
        verify(repository).deleteAll();
    }

    @Test
    public void testGetAllWithNoRecords() {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        List<Temperature> result = service.getAll();
        assertEquals(0, result.size());
    }

    @Test
    public void testGetByCodeWithNoMatchingRecords() {
        when(repository.findAllByCode("nonexistent")).thenReturn(Collections.emptyList());

        List<Temperature> result = service.getByCode("nonexistent");
        assertEquals(0, result.size());
    }

    @Test
    public void testDeleteAllException() {
        doThrow(new RuntimeException("Database error")).when(repository).deleteAll();

        assertThrows(RuntimeException.class, () -> {
            service.deleteAll();
        });
    }
}