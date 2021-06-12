package com.craftingdatascience.bbl.etl.infrastructure.config;

import com.craftingdatascience.bbl.etl.domain.FileReader;
import com.craftingdatascience.bbl.etl.domain.models.SourceComposition;
import com.craftingdatascience.bbl.etl.domain.models.SourceProduct;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

class SourceFileReadersTest {

    @Test
    void ProductsCsvReader_should_parse_the_correct_number_of_lines() throws IOException {
        // Given
        InputStream testFile = Files.newInputStream(ResourceUtils.getFile("classpath:models.csv").toPath());
        FileReader<SourceProduct> sourceProductCsvReader = new SourceFileReaders().productsCsvReader();

        // When
        List<SourceProduct> sourceProducts = sourceProductCsvReader.read(testFile).collect(toList());

        // Then
        assertThat(sourceProducts).hasSize(2);
    }

    @Test
    void ProductsCsvReader_should_map_row_to_pojo() throws IOException {
        // Given
        InputStream testFile = Files.newInputStream(ResourceUtils.getFile("classpath:models.csv").toPath());
        FileReader<SourceProduct> sourceProductCsvReader = new SourceFileReaders().productsCsvReader();

        // When
        SourceProduct sourceProduct = sourceProductCsvReader.read(testFile).collect(toList()).get(0);

        // Then
        assertThat(sourceProduct.getProductCode()).isEqualTo("1");
        assertThat(sourceProduct.getName()).isEqualTo("MaxiCookie");
        assertThat(sourceProduct.getUnitOfMeasure()).isEqualTo("kg");
        assertThat(sourceProduct.getBrand()).isEqualTo("A COOKIE COMPANY");
        assertThat(sourceProduct.getDetailCategory()).isEqualTo("Cakes|Snacks|Cookies");
    }

    @Test
    void compositionsCsvReader_should_parse_the_correct_number_of_lines() throws IOException {
        // Given
        InputStream testFile = Files.newInputStream(ResourceUtils.getFile("classpath:compositions.csv").toPath());
        FileReader<SourceComposition> sourceCompositionCsvReader = new SourceFileReaders().compositionsCsvReader();

        // When
        List<SourceComposition> sourceCompositions = sourceCompositionCsvReader.read(testFile).collect(toList());

        // Then
        assertThat(sourceCompositions).hasSize(5);
    }

    @Test
    void compositionsCsvReader_should_map_row_to_pojo() throws IOException {
        // Given
        InputStream testFile = Files.newInputStream(ResourceUtils.getFile("classpath:compositions.csv").toPath());
        FileReader<SourceComposition> sourceCompositionCsvReader = new SourceFileReaders().compositionsCsvReader();

        // When
        SourceComposition sourceComposition = sourceCompositionCsvReader.read(testFile).collect(toList()).get(0);

        // Then
        assertThat(sourceComposition.getProductCode()).isEqualTo("1");
        assertThat(sourceComposition.getName()).isEqualTo("MaxiCookie");
        assertThat(sourceComposition.getIngredientCode()).isEqualTo("34");
        assertThat(sourceComposition.getIngredientName()).isEqualTo("Sugar");
        assertThat(sourceComposition.getQuantity()).isEqualTo("100");
        assertThat(sourceComposition.getUnitOfMeasure()).isEqualTo("g/kg");
    }
}
