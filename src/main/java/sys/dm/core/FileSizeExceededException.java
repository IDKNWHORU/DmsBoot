package sys.dm.core;

import java.io.Serial;

public class FileSizeExceededException extends Throwable {
    @Serial
    private static final long serialVersionUID = 1L;

    public FileSizeExceededException() {
        super();
    }

    public FileSizeExceededException(String arg0) {
        super(arg0);
    }
}
