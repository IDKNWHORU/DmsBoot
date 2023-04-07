package sys.dm.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class TempFile implements AutoCloseable {
    private static final Logger log = LoggerFactory.getLogger(TempFile.class);

    private final File file;

    public TempFile(String prefix, String suffix) throws IOException {
        file = File.createTempFile(prefix, suffix);
        log.debug("TempFile.ctor(): file = {}", file);
    }

    public File getFile() {
        return file;
    }

    @Override
    public void close() {
        boolean isDeleted = file.delete();
        log.debug("TempFile.close(): file.delete() returned {}", isDeleted);
    }
}
