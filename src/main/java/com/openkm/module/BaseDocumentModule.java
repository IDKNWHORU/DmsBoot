package com.openkm.module;

import com.openkm.bean.*;
import com.openkm.core.Config;
import com.openkm.dto.NodeDocumentDTO;
import com.openkm.repository.NodeDocumentRepository;
import com.openkm.repository.NodeDocumentVersionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.*;

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
        Document document = new Document(nodeDocument.title());

        return document;
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
