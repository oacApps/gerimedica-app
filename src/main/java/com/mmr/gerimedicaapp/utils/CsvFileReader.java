package com.mmr.gerimedicaapp.utils;

import com.mmr.gerimedicaapp.domain.model.Temperature;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CsvFileReader {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    public List<Temperature> readCsvFile(MultipartFile file) throws IOException {
        Reader reader = new InputStreamReader(file.getInputStream());
        CSVParser csvParser = null;
        try {
            csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
            return parseRecord(csvParser);
        } catch (IOException e) {
            log.error("{}{}", ResponseMessage.ERROR_READING_FILE.getMessage(), e.getMessage());
            throw new IOException(e);
        }
    }

    private List<Temperature> parseRecord(CSVParser csvParser) {

        List<Temperature> temperatures = new ArrayList<>();

        // Skip the header line
        Iterator<CSVRecord> iterator = csvParser.iterator();
        if (iterator.hasNext()) {
            iterator.next();
        }

        while (iterator.hasNext()) {
            CSVRecord record = iterator.next();
            Temperature temperature = new Temperature();
            temperature.setCode(record.get(0));
            temperature.setSource(record.get(1));
            temperature.setCodeListCode(record.get(2));
            temperature.setDisplayValue(record.get(3));
            temperature.setLongDescription(record.get(4));
            temperature.setFromDate(record.get(5));
            temperature.setToDate(record.get(6));
            temperature.setSortingPriority(record.get(7));
            temperatures.add(temperature);
        }
        return temperatures;
    }
}
