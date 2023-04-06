package sys.dm.document;

import sys.dm.api.OKMDocument;
import sys.dm.bean.Document;

import java.io.FileInputStream;
import java.io.InputStream;

public class DocumentTest {
    public static void main(String... args) {
        InputStream is;

        try {
            is = new FileInputStream("D:\\test.txt");
            Document doc = new Document("test document123");
            doc.setPath("/okm:root/test.txt");
            doc.getKeywords().add("key1");
            doc.getKeywords().add("key2");
            Document newDoc = OKMDocument.INSTANCE.create(null, doc, is);
            System.out.println(doc.equals(newDoc));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
