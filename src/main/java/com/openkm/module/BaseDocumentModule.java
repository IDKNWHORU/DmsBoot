package com.openkm.module;

import com.openkm.bean.Document;
import com.openkm.bean.FileUploadResponse;
import com.openkm.bean.NodeBase;
import com.openkm.bean.NodeDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.*;

public class BaseDocumentModule {
    private static final Logger log = LoggerFactory.getLogger(BaseDocumentModule.class);

    public static Document getProperties(String user, NodeDocument nodeDocument) {
        log.debug("getProperties({}, {})", user, nodeDocument);
        long begin = System.currentTimeMillis();
        Document document = new Document(nodeDocument.title());

        return document;
    }

    public static NodeDocument create(String title) {
        return new NodeDocument(UUID.randomUUID().toString(), null, null, null, null, title, null, null, null);
    }

    public static <E> NodeDocument create(String user, String parentPath, NodeBase parentNode, String name, String title, Calendar created,
                                          String mimeType, InputStream is, long size, Set<String> keywords, HashSet<E> categories, HashSet<E> propertyGroups,
                                          ArrayList<E> notes, Object o1, FileUploadResponse fuResponse) {


        return new NodeDocument(UUID.randomUUID().toString(), parentNode.getContext(), parentNode.getUuid(), user, name, title, mimeType, Calendar.getInstance(), Calendar.getInstance());
    }
}
