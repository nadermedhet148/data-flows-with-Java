package com.craftingdatascience.bbl.etl.Jobs;

import com.craftingdatascience.bbl.etl.Jobs.ETLS.BrandProductCountETL;
import com.craftingdatascience.bbl.etl.Jobs.ETLS.ProductETL;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.ApplicationArguments;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductETLRunnerTest {

    @Mock
    private ProductETL importer;

    @Mock
    private BrandProductCountETL bandProductCountETL;

    @Test
    void ImporterRunner_should_run_importer() {
        // Given
        JobsRunner importerRunner = new JobsRunner(importer,bandProductCountETL);

        // When
        importerRunner.run(mock(ApplicationArguments.class));

        // Then
        verify(importer).run();
    }
}
