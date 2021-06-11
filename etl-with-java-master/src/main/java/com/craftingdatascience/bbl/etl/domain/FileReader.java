package com.craftingdatascience.bbl.etl.domain;

import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Stream;

public interface FileReader<T> {
    Stream<T> read(InputStream inputStream) throws IOException;
}
