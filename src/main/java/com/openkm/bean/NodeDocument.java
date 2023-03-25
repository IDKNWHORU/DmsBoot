package com.openkm.bean;

public record NodeDocument(
        String uuid,
        String title
) {
    private static final long serialVersionUID = 1L;
    public static final String TEXT_FIELD = "text";


}
