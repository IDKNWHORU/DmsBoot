package com.openkm.module;

import com.openkm.bean.Document;

public class DocumentModule {
    public Document create(String token, Document doc, java.io.InputStream is) {
        return new Document(doc.title());
    }
}
