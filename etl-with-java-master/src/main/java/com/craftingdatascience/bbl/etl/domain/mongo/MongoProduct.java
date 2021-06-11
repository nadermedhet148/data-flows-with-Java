package com.craftingdatascience.bbl.etl.domain.mongo;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class MongoProduct {
    private int code;
    private String name;
    private String brand;
    private String measureUnit;
    private List<String> categories;
    private List<Composition> compositions;

    @Builder
    @Getter
    public static class Composition {
        private String name;
        private float quantity;
        private String measureUnit;
    }
}
