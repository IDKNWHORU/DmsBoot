package com.openkm.module;

import com.openkm.bean.Document;
import com.openkm.bean.NodeDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DocumentModule {
    private static final Logger log = LoggerFactory.getLogger(DocumentModule.class);
    public Document create(String token, Document doc, java.io.InputStream is) {
        log.debug("create({}, {}, {})", token, doc, is);
        long begin = System.currentTimeMillis();
        Document newDocument = null;
        NodeDocument docNode = BaseDocumentModule.create(doc.title());

        newDocument = BaseDocumentModule.getProperties("", docNode);

        return newDocument;
    }
}
