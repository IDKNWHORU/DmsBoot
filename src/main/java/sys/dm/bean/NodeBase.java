package sys.dm.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;

import java.util.UUID;

@Entity
public class NodeBase {

    @Id
    private UUID uuid;

    private String path;

    @Builder
    public NodeBase(String path) {
        this.path = path;
    }

    public String getContext() {
        return "";
    }

    public UUID getUuid() {
        return UUID.randomUUID();
    }

    public String getPath() {
        return "";
    }
}
