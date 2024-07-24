package com.mmr.gerimedicaapp.utils;

import com.mmr.gerimedicaapp.domain.model.Temperature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CsvFileReaderTest {

    @Test
    public void testReadCsvFile() throws IOException {
        // Mock MultipartFile
        MultipartFile file = Mockito.mock(MultipartFile.class);

        // Prepare mock CSV content
        String csvContent = "Code,Source,CodeListCode,DisplayValue,LongDescription,FromDate,ToDate,SortingPriority\n" +
                "001,SourceA,Code1,Display1,Description1,2024-01-01,2024-12-31,1\n" +
                "002,SourceB,Code2,Display2,Description2,2024-01-01,2024-12-31,2";
        InputStream inputStream = new ByteArrayInputStream(csvContent.getBytes());
        when(file.getInputStream()).thenReturn(inputStream);

        // Create an instance of CsvFileReader
        CsvFileReader csvFileReader = new CsvFileReader();

        // Call the method under test
        List<Temperature> temperatures = csvFileReader.readCsvFile(file);

        // Expected result
        List<Temperature> expectedTemperatures = new ArrayList<>();
        Temperature temp1 = new Temperature();
        temp1.setCode("001");
        temp1.setSource("SourceA");
        temp1.setCodeListCode("Code1");
        temp1.setDisplayValue("Display1");
        temp1.setLongDescription("Description1");
        temp1.setFromDate("2024-01-01");
        temp1.setToDate("2024-12-31");
        temp1.setSortingPriority("1");
        expectedTemperatures.add(temp1);

        Temperature temp2 = new Temperature();
        temp2.setCode("002");
        temp2.setSource("SourceB");
        temp2.setCodeListCode("Code2");
        temp2.setDisplayValue("Display2");
        temp2.setLongDescription("Description2");
        temp2.setFromDate("2024-01-01");
        temp2.setToDate("2024-12-31");
        temp2.setSortingPriority("2");
        expectedTemperatures.add(temp2);

        // Verify the result
        assertEquals(expectedTemperatures, temperatures);
    }

    @Test
    public void testReadCsvFile_EmptyFile() throws IOException {
        MultipartFile file = Mockito.mock(MultipartFile.class);
        String csvContent = "";
        InputStream inputStream = new ByteArrayInputStream(csvContent.getBytes());
        when(file.getInputStream()).thenReturn(inputStream);

        CsvFileReader csvFileReader = new CsvFileReader();
        List<Temperature> temperatures = csvFileReader.readCsvFile(file);

        assertEquals(new ArrayList<>(), temperatures);
    }

    @Test
    public void testReadCsvFile_IncorrectDataFormats() throws IOException {
        MultipartFile file = Mockito.mock(MultipartFile.class);
        String csvContent = "Code,Source,CodeListCode,DisplayValue,LongDescription,FromDate,ToDate,SortingPriority\n" +
                "001,SourceA,Code1,Display1,Description1,InvalidDate,2024-12-31,InvalidPriority"; // Invalid date and priority
        InputStream inputStream = new ByteArrayInputStream(csvContent.getBytes());
        when(file.getInputStream()).thenReturn(inputStream);

        CsvFileReader csvFileReader = new CsvFileReader();
        List<Temperature> temperatures = csvFileReader.readCsvFile(file);

        assertEquals(1, temperatures.size());
    }

    @Test
    public void testReadCsvFile_OnlyHeaders() throws IOException {
        MultipartFile file = Mockito.mock(MultipartFile.class);
        String csvContent = "Code,Source,CodeListCode,DisplayValue,LongDescription,FromDate,ToDate,SortingPriority\n"; // Only headers
        InputStream inputStream = new ByteArrayInputStream(csvContent.getBytes());
        when(file.getInputStream()).thenReturn(inputStream);

        CsvFileReader csvFileReader = new CsvFileReader();
        List<Temperature> temperatures = csvFileReader.readCsvFile(file);

        assertEquals(new ArrayList<>(), temperatures);
    }

    @Test
    public void testReadCsvFile_ExceptionHandling() throws IOException {
        MultipartFile file = Mockito.mock(MultipartFile.class);
        when(file.getInputStream()).thenThrow(new IOException("Simulated IOException"));

        CsvFileReader csvFileReader = new CsvFileReader();

        try {
            csvFileReader.readCsvFile(file);
        } catch (IOException e) {
            assertEquals("Simulated IOException", e.getMessage());
        }
    }




}