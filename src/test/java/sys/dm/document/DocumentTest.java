package sys.dm.document;

import sys.dm.api.OKMDocument;
import sys.dm.bean.Document;
import sys.dm.bean.Node;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

public class DocumentTest {
    public static void main(String... args) {
        InputStream is;

        try {
            is = new FileInputStream("D:\\test.txt");
            Node newNode = new Node();
            Node keywordAddedNode = newNode.addKeyword("key1").addKeyword("key2");

            Document doc = new Document(UUID.randomUUID(), "test document123", "/okm:root", "text", keywordAddedNode);
            Document newDoc = OKMDocument.INSTANCE.create(null, doc, is);
            System.out.println(doc.equals(newDoc));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
