package com.craftingdatascience.bbl.etl.Jobs;

import com.craftingdatascience.bbl.etl.Jobs.ETLS.BrandProductCountETL;
import com.craftingdatascience.bbl.etl.Jobs.ETLS.ProductETL;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class JobsRunner implements ApplicationRunner {
    private final Runnable importer;
    private final BrandProductCountETL brandProductCountETL;
    public JobsRunner(
            ProductETL importer,
            BrandProductCountETL brandProductCountETL
    ) {
        this.importer = importer;
        this.brandProductCountETL = brandProductCountETL;
    }

    public void run(ApplicationArguments args) {
        importer.run();
        brandProductCountETL.run();
    }
}
