package com.openkm.module;

import com.openkm.bean.Document;
import com.openkm.bean.NodeDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Set;
import java.util.UUID;

public class BaseDocumentModule {
    private static final Logger log = LoggerFactory.getLogger(BaseDocumentModule.class);
    public static Document getProperties(String user, NodeDocument nodeDocument) {
        log.debug("getProperties({}, {})", user, nodeDocument);
        long begin = System.currentTimeMillis();
        Document document = new Document(nodeDocument.title());

        return document;
    }

    public static NodeDocument create(String title) {
        return new NodeDocument(UUID.randomUUID().toString(), title);
    }
}
