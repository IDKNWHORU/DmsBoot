package sys.dm.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Calendar;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class NodeDocument {
    @Id
    private UUID uuid;
    private String context;
    private UUID parent;
    private String author;
    private String name;
    private String title;
    private String mimeType;
    private Calendar created;
    private Calendar lastModified;
    private String path;

    @Builder
    public NodeDocument(UUID uuid, String context, UUID parent, String author, String name, String title, String mimeType, Calendar created, Calendar lastModified, String path) {
        this.uuid = uuid;
        this.context = context;
        this.parent = parent;
        this.author = author;
        this.name = name;
        this.title = title;
        this.mimeType = mimeType;
        this.created = created;
        this.lastModified = lastModified;
        this.path = path;
    }
}
