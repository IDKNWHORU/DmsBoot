package sys.dm.core;

import java.io.Serial;

public class UnsupportedMimeTypeException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public UnsupportedMimeTypeException() {
        super();
    }

    public UnsupportedMimeTypeException(String arg0) {
        super(arg0);
    }
}
