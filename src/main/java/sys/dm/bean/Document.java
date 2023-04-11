package sys.dm.bean;

import java.util.Calendar;
import java.util.UUID;

public record Document(
        UUID uuid,
        String title,
        String path,
        String mimeType,
        Node node
) {

    @Override
    public String toString() {
        String serializedEntity = """
                Document{title='%s', path='%s', keywords=%s}
                """;

        return String.format(serializedEntity, title, node.getPath(), node.getKeywords());
    }

    public Calendar getCreated() {
        return Calendar.getInstance();
    }
}
