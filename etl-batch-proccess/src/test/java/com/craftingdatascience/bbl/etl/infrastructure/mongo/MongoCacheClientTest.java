package com.craftingdatascience.bbl.etl.infrastructure.mongo;

import com.craftingdatascience.bbl.etl.domain.mongo.MongoProduct;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.ReplaceOptions;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MongoCacheClientTest {

    @Mock
    private MongoClient mongoClient;

    @Mock
    private MongoDatabase mongoDatabase;

    @Mock
    private MongoCollection<MongoProduct> productsCollection;

    @Captor
    private ArgumentCaptor<MongoProduct> mongoProductArgumentCaptor;

    @Test
    void send_should_call_replaceOne_on_appropriate_collection() {
        // Given
        String productsCollectionName = "products";
        String databaseName = "my-database";
        MongoProduct mongoProduct = MongoProduct.builder().code(1).build();

        when(mongoClient.getDatabase(databaseName)).thenReturn(mongoDatabase);

        doReturn(productsCollection).when(mongoDatabase).getCollection(
                productsCollectionName,
                MongoProduct.class
        );

        MongoProperties mongoProperties = new MongoProperties();
        mongoProperties.setDatabaseName(databaseName);
        mongoProperties.setCollectionProductsName(productsCollectionName);

        // When
        MongoCacheClient mongoCacheClient = new MongoCacheClient(mongoClient, mongoProperties);
        mongoCacheClient.send(mongoProduct);

        // Then
        verify(productsCollection).replaceOne(
                any(Bson.class),
                mongoProductArgumentCaptor.capture(),
                any(ReplaceOptions.class)
        );

        assertThat(mongoProductArgumentCaptor.getValue()).isEqualTo(mongoProduct);
    }
}