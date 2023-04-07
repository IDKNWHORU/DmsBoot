package sys.dm.api;

import sys.dm.bean.Document;
import sys.dm.module.DocumentModule;
import sys.dm.module.ModuleManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public enum OKMDocument {
    INSTANCE;
    private static final Logger log = LoggerFactory.getLogger(OKMDocument.class);

    OKMDocument() {
    }

    public Document create(String token, Document doc, InputStream is) {
        log.debug("create({}, {}, {})", token, doc, is);
        DocumentModule documentModule = ModuleManager.getDocumentModule();
        Document newDocument = documentModule.create(token, doc, is);
        log.debug("create: {}", newDocument);
        return newDocument;
    }
}
