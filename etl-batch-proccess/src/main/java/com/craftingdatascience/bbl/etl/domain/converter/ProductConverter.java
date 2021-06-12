package com.craftingdatascience.bbl.etl.domain.converter;

import com.craftingdatascience.bbl.etl.domain.mongo.MongoProduct;
import com.craftingdatascience.bbl.etl.domain.models.SourceComposition;
import com.craftingdatascience.bbl.etl.domain.models.SourceProduct;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductConverter {

    public MongoProduct adapt(
            SourceProduct sourceProduct,
            List<SourceComposition> sourceCompositions
    ) {
        return MongoProduct.builder()
                .code(Integer.parseInt(sourceProduct.getProductCode()))
                .name(sourceProduct.getName())
                .brand(sourceProduct.getBrand())
                .measureUnit(sourceProduct.getUnitOfMeasure())
                .categories(getCategories(sourceProduct))
                .compositions(getCompositions(sourceCompositions))
                .build();
    }

    private List<String> getCategories(SourceProduct sourceProduct) {
        return Optional.ofNullable(sourceProduct.getDetailCategory())
                .map(categories -> categories.split("\\|"))
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }

    private List<MongoProduct.Composition> getCompositions(List<SourceComposition> sourceCompositions) {
        return sourceCompositions.stream()
                .map(sourceComposition -> MongoProduct.Composition.builder()
                        .name(sourceComposition.getIngredientName())
                        .measureUnit(sourceComposition.getUnitOfMeasure())
                        .quantity(Float.parseFloat(sourceComposition.getQuantity()))
                        .build()
                )
                .collect(Collectors.toList());
    }
}
