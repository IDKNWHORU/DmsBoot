package com.openkm.core;

public class FileSizeExceededException extends Throwable {
    private static final long serialVersionUID = 1L;

    public FileSizeExceededException() {
        super();
    }

    public FileSizeExceededException(String arg0) {
        super(arg0);
    }
}
