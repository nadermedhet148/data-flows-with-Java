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
                "" +
                        ""
        }
)
public class SourceBrandProductCount {

    @JsonProperty("Brand")
    private String brand;
    @JsonProperty("DetailCategory")
    private Integer count;
}
