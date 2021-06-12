package com.craftingdatascience.bbl.etl.infrastructure.config;

import com.craftingdatascience.bbl.etl.domain.FileReader;
import com.craftingdatascience.bbl.etl.domain.models.SourceComposition;
import com.craftingdatascience.bbl.etl.domain.models.SourceProduct;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStreamReader;

import static java.nio.charset.StandardCharsets.ISO_8859_1;

@Configuration
public class SourceFileReaders {

    @Bean
    public FileReader<SourceProduct> productsCsvReader() {
        return inputStream -> getObjectReader(SourceProduct.class)
                .<SourceProduct>readValues(new InputStreamReader(inputStream, ISO_8859_1)).readAll().stream();
    }

    @Bean
    public FileReader<SourceComposition> compositionsCsvReader() {

        return inputStream -> getObjectReader(SourceComposition.class)
                .<SourceComposition>readValues(new InputStreamReader(inputStream, ISO_8859_1)).readAll().stream();
    }

    private ObjectReader getObjectReader(Class<?> pojoType) {
        CsvMapper csvMapper = new CsvMapper();
        return csvMapper
                .readerFor(pojoType)
                .with(csvMapper.schemaFor(pojoType)
                        .withColumnSeparator(';')
                        .withHeader()
                );
    }

}
