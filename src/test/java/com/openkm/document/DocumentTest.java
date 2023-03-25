package com.openkm.document;

import com.openkm.api.OKMDocument;
import com.openkm.bean.Document;

import java.io.FileInputStream;
import java.io.InputStream;

public class DocumentTest {
    public static void main(String... args) {
        InputStream is;

        try {
            is = new FileInputStream("D:\\test.txt");
            Document doc = new Document("test document");
            doc.setPath("/okm:root/test.txt");
            doc.getKeywords().add("key1");
            doc.getKeywords().add("key2");
            doc = OKMDocument.getInstance().create(null, doc, is);
            System.out.println(doc);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
