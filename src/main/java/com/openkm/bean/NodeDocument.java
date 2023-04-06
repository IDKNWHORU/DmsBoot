package com.openkm.bean;

import java.util.Calendar;
import java.util.UUID;

public record NodeDocument(
        String uuid,
        String context,
        UUID parent,
        String author,
        String name,
        String title,
        String mimeType,
        Calendar created,
        Calendar lastModified
) {
    private static final long serialVersionUID = 1L;
    public static final String TEXT_FIELD = "text";


}
