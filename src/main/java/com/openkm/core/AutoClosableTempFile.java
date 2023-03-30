package com.openkm.core;

import java.io.File;
import java.io.IOException;

public class AutoClosableTempFile implements AutoCloseable {

    private final File file;

    public AutoClosableTempFile() throws IOException {
        file = File.createTempFile("okm", ".tmp");
    }

    public File getFile() {
        return file;
    }

    @Override
    public void close() throws IOException {
        file.delete();
    }
}
