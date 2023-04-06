package com.openkm;

import com.openkm.bean.NodeDocument;
import com.openkm.repository.NodeDocumentRepository;
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

    @Test
    void contextLoads() {
    }

    @Test
    void test() {
        NodeDocument nodeDocument = NodeDocument.builder().uuid(UUID.randomUUID()).build();

        log.info("node document is: {}", nodeDocument.toString());
        nodeDocumentRepository.save(nodeDocument);
    }
}
