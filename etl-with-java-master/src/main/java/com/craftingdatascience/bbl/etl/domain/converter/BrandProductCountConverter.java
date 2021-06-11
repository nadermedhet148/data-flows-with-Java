package com.craftingdatascience.bbl.etl.domain.converter;
import com.craftingdatascience.bbl.etl.domain.models.SourceProduct;
import com.craftingdatascience.bbl.etl.domain.mongo.MongoBrandProductCount;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

public class BrandProductCountConverter {
    public MongoBrandProductCount adapt(
            List<SourceProduct> sourceProducts
    ) {
        return MongoBrandProductCount.builder()
                .brand(sourceProducts.get(0).getBrand())
                .count(sourceProducts.size())
                .build();
    }



}
