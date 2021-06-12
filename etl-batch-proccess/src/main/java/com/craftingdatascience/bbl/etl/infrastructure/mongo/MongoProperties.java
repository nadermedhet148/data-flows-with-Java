package com.craftingdatascience.bbl.etl.infrastructure.mongo;

import lombok.Data;

@Data
public class MongoProperties {
    private String uri;
    private String databaseName;
    private String collectionProductsName;
    private String collectionBrandProductCount;

}
