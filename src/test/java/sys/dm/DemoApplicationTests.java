package sys.dm;

import org.junit.jupiter.api.BeforeAll;
import sys.dm.bean.NodeDocument;
import sys.dm.repository.NodeDocumentRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class DemoApplicationTests {
    private static final Logger log = LoggerFactory.getLogger(DemoApplicationTests.class);
    @Autowired
    NodeDocumentRepository nodeDocumentRepository;

    @BeforeAll
    public static void cleanup(@Autowired NodeDocumentRepository nodeDocumentRepository) {
        NodeDocument clearDocument = NodeDocument.builder().uuid(UUID.fromString("033f1451-4d29-477a-876e-ef94dc6d606c")).build();
        log.info("deleted document is: {}", clearDocument.toString());

        nodeDocumentRepository.delete(clearDocument);
    }

    @Test
    void test() {
        NodeDocument nodeDocument = NodeDocument.builder().uuid(UUID.fromString("033f1451-4d29-477a-876e-ef94dc6d606c")).build();

        log.info("node document is: {}", nodeDocument.toString());
        NodeDocument savedDocument = nodeDocumentRepository.save(nodeDocument);

        assert savedDocument.getUuid().equals(nodeDocument.getUuid());
    }
}
