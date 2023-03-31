package com.openkm.module;

import com.openkm.bean.Document;
import com.openkm.bean.FileUploadResponse;
import com.openkm.bean.NodeDocument;
import com.openkm.core.RepositoryException;
import com.openkm.util.PathUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

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

    public Document create(String token, Document doc, InputStream is, long size, String userId, FileUploadResponse fuResponse) throws Exception {
        return create(token, doc, is, size, userId, fuResponse, null);
    }

    public Document create(String token, Document doc, InputStream is, long size, String userId, FileUploadResponse fuResponse, String emailSubject) throws Exception {
        log.debug("create({}, {}, {}, {}, {}, {}, {})", token, doc, is, size, userId, fuResponse, emailSubject);
        long begin = System.currentTimeMillis();
        Document newDocument = null;

        if (!PathUtils.checkPath(doc.getPath())) {
            throw new RepositoryException("Invalid path: " + doc.getPath());
        }

        String parentPath = null;
        String name = null;

        // process emailSubject is null
//        if(emailSubject != null && !emailSubject.isEmpty()) {
//            String escapedSub
//            name = emailSubject;
//        } else {
//            name = doc.getTitle();
//        }
        if (parentPath == null || name == null) {
            parentPath = PathUtils.getParent(doc.getPath());
            name = PathUtils.getName(doc.getPath());
        }

        int extIdx = name.lastIndexOf(".");
        String fileExtension = extIdx > 0 ? name.substring(extIdx) : ".tmp";

        return new Document("");
    }
}
