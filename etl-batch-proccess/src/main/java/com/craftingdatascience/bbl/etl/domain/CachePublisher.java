package com.craftingdatascience.bbl.etl.domain;

import com.craftingdatascience.bbl.etl.domain.mongo.MongoBrandProductCount;
import com.craftingdatascience.bbl.etl.domain.mongo.MongoProduct;

public interface CachePublisher {
    void send(MongoProduct mongoProduct);
    void send(MongoBrandProductCount mongoBrandProductCount);

}
