package com.openkm.core;

import java.io.File;
import java.io.IOException;

public class AutoClosableTempFile implements AutoCloseable {

    private final File file;

    public AutoClosableTempFile(String prefix, String suffix) throws IOException {
        file = File.createTempFile(prefix, suffix);
    }

    public File getFile() {
        return file;
    }

    @Override
    public void close() throws IOException {
        file.delete();
    }
}
