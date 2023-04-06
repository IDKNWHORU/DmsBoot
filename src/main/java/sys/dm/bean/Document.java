package sys.dm.bean;

import java.util.Calendar;
import java.util.Set;

public record Document(
        String title
) {
    private static final Node node = new Node();

    public void setPath(String path) {
        node.setPath(path);
    }

    public String getPath() {
        return node.getPath();
    }

    public Set<String> getKeywords() {
        return node.getKeywords();
    }

    public Calendar getCreated() {
        return null;
    }

    @Override
    public String toString() {
        String serializedEntity = """
                Document{title='%s', path='%s', keywords=%s}
                """;

        return String.format(serializedEntity, title, node.getPath(), node.getKeywords());
    }

    public String getUuid() {
        return "";
    }

    public void setMimeType(String mimeType) {

    }
}
