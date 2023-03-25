package com.openkm.api;

import com.openkm.bean.Document;

public class OKMDocument {
    private static OKMDocument instance = new OKMDocument();

    public static OKMDocument getInstance() {
        return instance;
    }

    private OKMDocument() {
    }

    public Document create(String token, Document doc, java.io.InputStream is) {
        return null;
    }
}
