package com.openkm.api;

import com.openkm.bean.Document;
import com.openkm.module.DocumentModule;
import com.openkm.module.ModuleManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OKMDocument {
    private static final Logger log = LoggerFactory.getLogger(OKMDocument.class);
    private static final OKMDocument instance = new OKMDocument();

    public static OKMDocument getInstance() {
        return instance;
    }

    private OKMDocument() {
    }

    public Document create(String token, Document doc, java.io.InputStream is) {
        log.debug("create({}, {}, {})", token, doc, is);
        DocumentModule documentModule = ModuleManager.getDocumentModule();
        Document newDocument = documentModule.create(token, doc, is);
        log.debug("create: {}", newDocument);
        return newDocument;
    }
}
