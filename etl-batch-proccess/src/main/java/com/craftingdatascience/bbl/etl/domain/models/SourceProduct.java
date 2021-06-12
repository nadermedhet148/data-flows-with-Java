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
                "UnitOfMeasure",
                "Brand",
                "DetailCategory"
        }
)
public class SourceProduct {
    @JsonProperty("ProductCode")
    private String productCode;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("UnitOfMeasure")
    private String unitOfMeasure;
    @JsonProperty("Brand")
    private String brand;
    @JsonProperty("DetailCategory")
    private String DetailCategory;
}
