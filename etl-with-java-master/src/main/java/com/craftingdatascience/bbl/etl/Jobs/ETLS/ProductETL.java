package com.craftingdatascience.bbl.etl.Jobs.ETLS;

import com.craftingdatascience.bbl.etl.domain.CachePublisher;
import com.craftingdatascience.bbl.etl.domain.FileReader;
import com.craftingdatascience.bbl.etl.domain.converter.ProductConverter;
import com.craftingdatascience.bbl.etl.domain.mongo.MongoProduct;
import com.craftingdatascience.bbl.etl.domain.models.SourceComposition;
import com.craftingdatascience.bbl.etl.domain.models.SourceProduct;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.groupingBy;

@Component
public class ProductETL implements Runnable {

    private final FileReader<SourceProduct> sourceProductFileReader;
    private final FileReader<SourceComposition> sourceCompositionFileReader;
    private final ProductConverter ProductConverter;
    private final CachePublisher cachePublisher;

    public ProductETL(
            FileReader<SourceProduct> sourceProductFileReader,
            FileReader<SourceComposition> sourceCompositionFileReader,
            ProductConverter ProductConverter,
            CachePublisher cachePublisher
    ) {
        this.sourceProductFileReader = sourceProductFileReader;
        this.sourceCompositionFileReader = sourceCompositionFileReader;
        this.ProductConverter = ProductConverter;
        this.cachePublisher = cachePublisher;
    }

    public void run() {
        try {
            System.out.println("Retrieving data from files...");
            InputStream productsInputStream = Files.newInputStream(ResourceUtils.getFile("classpath:models.csv").toPath());
            InputStream compositionsInputStream = Files.newInputStream(ResourceUtils.getFile("classpath:compositions.csv").toPath());

            System.out.println("Parsing data from compositions to POJOs...");
            Map<String, List<SourceComposition>> compositionsByProductCode = groupCompositionsByProductCode(compositionsInputStream);

            System.out.println("Parsing and converting product models...");
            sourceProductFileReader.read(productsInputStream)
                    .map(Product -> convert(compositionsByProductCode, Product))
                    .forEach(cachePublisher::send);
        } catch (IOException e) {
            System.out.println("Something went wrong here...");
        }
        System.out.println("Job's done!");
    }

    private Map<String, List<SourceComposition>> groupCompositionsByProductCode(InputStream compositionsInputStream) throws IOException {
        Stream<SourceComposition> sourceCompositionStream = sourceCompositionFileReader.read(compositionsInputStream);
        return sourceCompositionStream.collect(groupingBy(SourceComposition::getProductCode));
    }

    private MongoProduct convert(Map<String, List<SourceComposition>> compositionsByProductCode, SourceProduct Product) {
        List<SourceComposition> compositions = compositionsByProductCode.getOrDefault(
                Product.getProductCode(),
                emptyList()
        );
        return ProductConverter.adapt(Product, compositions);
    }
}
