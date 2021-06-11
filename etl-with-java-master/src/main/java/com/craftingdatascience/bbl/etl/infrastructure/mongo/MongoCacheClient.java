package com.craftingdatascience.bbl.etl.infrastructure.mongo;

import com.craftingdatascience.bbl.etl.domain.CachePublisher;
import com.craftingdatascience.bbl.etl.domain.mongo.MongoBrandProductCount;
import com.craftingdatascience.bbl.etl.domain.mongo.MongoProduct;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.ReplaceOptions;
import org.springframework.stereotype.Component;

import static com.mongodb.client.model.Filters.eq;

@Component
public class MongoCacheClient implements CachePublisher {

    private final MongoClient mongoClient;
    private final MongoProperties mongoProperties;

    public MongoCacheClient(
            MongoClient mongoClient,
            MongoProperties mongoProperties
    ) {
        this.mongoClient = mongoClient;
        this.mongoProperties = mongoProperties;
    }

    public void send(MongoProduct mongoProduct) {
        MongoDatabase database = mongoClient.getDatabase(mongoProperties.getDatabaseName());
        MongoCollection<MongoProduct> productsCollection = database.getCollection(
                mongoProperties.getCollectionProductsName(),
                MongoProduct.class
        );

        ReplaceOptions enableUpsert = new ReplaceOptions().upsert(true);
        productsCollection.replaceOne(
                eq("code", mongoProduct.getCode()),
                mongoProduct,
                enableUpsert
        );
    }
    public void send(MongoBrandProductCount mongoBrandProductCount) {
        MongoDatabase database = mongoClient.getDatabase(mongoProperties.getDatabaseName());
        MongoCollection<MongoBrandProductCount> brandProductCountCollection = database.getCollection(
                mongoProperties.getCollectionBrandProductCount(),
                MongoBrandProductCount.class
        );

        ReplaceOptions enableUpsert = new ReplaceOptions().upsert(true);
        brandProductCountCollection.replaceOne(
                eq("brand", mongoBrandProductCount.getBrand()),
                mongoBrandProductCount,
                enableUpsert
        );
    }
}
