package sys.dm.dto;

import sys.dm.bean.NodeBase;

import java.util.Calendar;
import java.util.UUID;

public record NodeDocumentDTO(
        UUID uuid,
        String context,
        NodeBase parent,
        String author,
        String name,
        String title,
        String mimeType,
        Calendar created,
        Calendar lastModified,
        String path
) {
    private static final long serialVersionUID = 1L;
    public static final String TEXT_FIELD = "text";
}
