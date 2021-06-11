package com.craftingdatascience.bbl.etl.domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder(
        {
                "ProductCode",
                "Name",
                "IngredientCode",
                "IngredientName",
                "Quantity",
                "UnitOfMeasure"
        }
)
public class SourceComposition {
    @JsonProperty("ProductCode")
    private String productCode;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("IngredientCode")
    private String ingredientCode;
    @JsonProperty("IngredientName")
    private String ingredientName;
    @JsonProperty("Quantity")
    private String quantity;
    @JsonProperty("UnitOfMeasure")
    private String unitOfMeasure;
}
