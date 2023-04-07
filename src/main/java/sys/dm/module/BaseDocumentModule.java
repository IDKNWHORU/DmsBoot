package sys.dm.module;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sys.dm.bean.Document;
import sys.dm.bean.FileUploadResponse;
import sys.dm.bean.NodeDocument;
import sys.dm.bean.NodeDocumentVersion;
import sys.dm.core.Config;
import sys.dm.dto.NodeDocumentDTO;
import sys.dm.repository.NodeDocumentRepository;
import sys.dm.repository.NodeDocumentVersionRepository;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class BaseDocumentModule {
    private static final Logger log = LoggerFactory.getLogger(BaseDocumentModule.class);
    private final NodeDocumentRepository nodeDocumentRepository;
    private final NodeDocumentVersionRepository nodeDocumentVersionRepository;

    public BaseDocumentModule(NodeDocumentRepository nodeDocumentRepository, NodeDocumentVersionRepository nodeDocumentVersionRepository) {
        this.nodeDocumentRepository = nodeDocumentRepository;
        this.nodeDocumentVersionRepository = nodeDocumentVersionRepository;
    }

    public static Document getProperties(String user, NodeDocumentDTO nodeDocument) {
        log.debug("getProperties({}, {})", user, nodeDocument);
        long begin = System.currentTimeMillis();

        return new Document(nodeDocument.title());
    }

    public static NodeDocumentDTO create(String title) {
        return new NodeDocumentDTO(UUID.randomUUID(),
                null,
                null,
                null,
                null,
                title,
                null,
                null,
                null,
                null);
    }

    public <E> NodeDocumentDTO create(NodeDocumentDTO nodeDocumentDTO,
                                      InputStream is, long size, Set<String> keywords, HashSet<E> categories, HashSet<E> propertyGroups,
                                      ArrayList<E> notes, Object o1, FileUploadResponse fuResponse) {
        String path = "";

        if (Config.STORE_NODE_PATH) {
            path = "%s/%s".formatted(nodeDocumentDTO.parent().getPath(), nodeDocumentDTO.name());
        }

        NodeDocument newDoc = NodeDocument.builder()
                .uuid(nodeDocumentDTO.uuid())
                .context(nodeDocumentDTO.context())
                .parent(nodeDocumentDTO.parent().getUuid())
                .author(nodeDocumentDTO.author())
                .name(nodeDocumentDTO.name())
                .title(nodeDocumentDTO.title())
                .mimeType(nodeDocumentDTO.mimeType())
                .created(nodeDocumentDTO.created())
                .lastModified(nodeDocumentDTO.lastModified())
                .path(path)
                .build();

        log.debug("newDoc uuid is {}", newDoc.getUuid());

        NodeDocumentVersion newDocVer = new NodeDocumentVersion();

        this.nodeDocumentRepository.save(newDoc);
        this.nodeDocumentVersionRepository.save(newDocVer);

        return nodeDocumentDTO;
    }
}
