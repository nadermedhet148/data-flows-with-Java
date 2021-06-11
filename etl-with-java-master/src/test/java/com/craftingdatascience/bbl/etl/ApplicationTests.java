package com.craftingdatascience.bbl.etl;

import com.craftingdatascience.bbl.etl.infrastructure.mongo.MongoCacheClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest()
public class ApplicationTests {
    @MockBean
    MongoCacheClient mongoCacheClientClient;

    @Test
    public void context_loads() {
    }

}
