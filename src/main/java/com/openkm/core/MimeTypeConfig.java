package com.openkm.core;

import jakarta.activation.MimetypesFileTypeMap;

public interface MimeTypeConfig {
    String MIME_PDF = "application/pdf";
    MimetypesFileTypeMap MIME_TYPES = new MimetypesFileTypeMap();
}
