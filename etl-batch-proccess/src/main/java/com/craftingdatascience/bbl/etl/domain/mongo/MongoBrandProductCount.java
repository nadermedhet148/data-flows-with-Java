package com.craftingdatascience.bbl.etl.domain.mongo;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class MongoBrandProductCount {
    private String brand;
    private Integer count;

}
