package sys.dm.core;

import java.io.Serial;

public class RepositoryException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public RepositoryException() {
        super();
    }

    public RepositoryException(String message) {
        super(message);
    }
}
