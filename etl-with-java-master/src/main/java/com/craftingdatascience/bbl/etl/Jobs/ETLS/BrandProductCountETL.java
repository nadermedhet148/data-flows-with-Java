package com.craftingdatascience.bbl.etl.Jobs.ETLS;

import com.craftingdatascience.bbl.etl.domain.CachePublisher;
import com.craftingdatascience.bbl.etl.domain.FileReader;
import com.craftingdatascience.bbl.etl.domain.converter.BrandProductCountConverter;
import com.craftingdatascience.bbl.etl.domain.models.SourceProduct;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.groupingBy;

@Component
public class BrandProductCountETL implements Runnable {

    private final FileReader<SourceProduct> sourceProductFileReader;
    private final com.craftingdatascience.bbl.etl.domain.converter.BrandProductCountConverter brandProductCountConverter;
    private final CachePublisher cachePublisher;

    public BrandProductCountETL(
            FileReader<SourceProduct> sourceProductFileReader,
            BrandProductCountConverter brandProductCountConverter,
            CachePublisher cachePublisher
    ) {
        this.sourceProductFileReader = sourceProductFileReader;
        this.brandProductCountConverter = brandProductCountConverter;
        this.cachePublisher = cachePublisher;
    }

    public void run() {
        try {
            System.out.println("Retrieving data from files...");
            InputStream productsInputStream = Files.newInputStream(ResourceUtils.getFile("classpath:models.csv").toPath());

            System.out.println("Parsing and converting product models...");
            Map<String, List<SourceProduct>> productsGroupedWithBrand = sourceProductFileReader.read(productsInputStream)
                    .collect(groupingBy(SourceProduct::getBrand));

            productsGroupedWithBrand.keySet().forEach(key->{
                cachePublisher.send(brandProductCountConverter.adapt(productsGroupedWithBrand.get(key)));
            });

        } catch (IOException e) {
            System.out.println("Something went wrong here...");
        }
        System.out.println("Job's done!");
    }


}
