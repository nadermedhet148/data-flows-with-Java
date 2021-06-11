package com.craftingdatascience.bbl.etl.domain.converter;

import com.craftingdatascience.bbl.etl.domain.mongo.MongoProduct;
import com.craftingdatascience.bbl.etl.domain.models.SourceComposition;
import com.craftingdatascience.bbl.etl.domain.models.SourceProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

class ProductConverterTest {
    private SourceProduct sourceProduct;

    @BeforeEach
    void setUp() {
        sourceProduct = new SourceProduct();
        sourceProduct.setProductCode("1");
        sourceProduct.setName("MaxiCookie");
        sourceProduct.setUnitOfMeasure("L");
        sourceProduct.setBrand("A COOKIE COMPANY");
        sourceProduct.setDetailCategory("Cookies");

    }

    @Test
    void adapt_should_create_a_MongoProduct_with_no_composition_if_empty_list() {
        // When
        MongoProduct mongoProduct = new ProductConverter().adapt(sourceProduct, emptyList());

        // Then
        assertThat(mongoProduct.getCode()).isEqualTo(1);
        assertThat(mongoProduct.getName()).isEqualTo("MaxiCookie");
        assertThat(mongoProduct.getBrand()).isEqualTo("A COOKIE COMPANY");
        assertThat(mongoProduct.getMeasureUnit()).isEqualTo("L");
        assertThat(mongoProduct.getCategories()).isEqualTo(singletonList("Cookies"));
    }

    @Test
    void adapt_should_create_a_MongoProduct_with_no_composition_and_split_categories() {
        // Given
        sourceProduct.setDetailCategory("Cookies|Snacks");

        // When
        MongoProduct mongoProduct = new ProductConverter().adapt(sourceProduct, emptyList());

        // Then
        assertThat(mongoProduct.getCategories()).isEqualTo(asList("Cookies", "Snacks"));
    }

    @Test
    void adapt_should_create_a_MongoProduct_compositions_if_provided() {
        SourceComposition sourceComposition = new SourceComposition();
        sourceComposition.setIngredientName("Sugar");
        sourceComposition.setQuantity("10");
        sourceComposition.setUnitOfMeasure("g/unit");

        // When
        MongoProduct mongoProduct = new ProductConverter().adapt(
                sourceProduct,
                singletonList(sourceComposition)
        );

        // Then
        MongoProduct.Composition composition = mongoProduct.getCompositions().get(0);
        assertThat(composition.getName()).isEqualTo("Sugar");
        assertThat(composition.getQuantity()).isEqualTo(10f);
        assertThat(composition.getMeasureUnit()).isEqualTo("g/unit");
    }
}