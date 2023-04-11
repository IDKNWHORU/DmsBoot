/**
 * OpenKM, Open Document Management System (http://www.openkm.com)
 * Copyright (c) Paco Avila & Josep Llort
 * <p>
 * No bytes were intentionally harmed during the development of this application.
 * <p>
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package sys.dm.module;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sys.dm.bean.*;
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

        return new Document(nodeDocument.uuid(), nodeDocument.title(), nodeDocument.path(), nodeDocument.mimeType(), new Node());
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
                                      ArrayList<E> notes, Object o1) {
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
