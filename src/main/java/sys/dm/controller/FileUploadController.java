package sys.dm.controller;

import sys.dm.api.OKMDocument;
import sys.dm.bean.Document;
import sys.dm.bean.FileUploadResponse;
import sys.dm.bean.Node;
import sys.dm.core.FileSizeExceededException;
import sys.dm.util.TempFile;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.UUID;

@RestController
public class FileUploadController {
    private static final Logger log = LoggerFactory.getLogger(FileUploadController.class);

    @PostMapping("/FileUpload")
    public ResponseEntity<String> post(HttpServletRequest request) throws IOException, FileSizeExceededException {
        log.debug("doPost({})", request);

        FileUploadResponse fuResponse = new FileUploadResponse(new ArrayList<>(), new ArrayList<>(), false, false, false, false, "", "");
        try (TempFile tempFileWrapper = new TempFile("okm", ".tmp");
             FileOutputStream fos = new FileOutputStream(tempFileWrapper.getFile());
             BufferedOutputStream bos = new BufferedOutputStream(fos);
             FileInputStream fis = new FileInputStream(tempFileWrapper.getFile())) {

            String path = request.getAttribute("path").toString();
            String data = request.getAttribute("data").toString();
            bos.write(Base64.getDecoder().decode(data));
            bos.flush();
            fos.flush();

            String newPath = path.substring(0, path.lastIndexOf(".") + 1) + "pdf";
            Document newDoc = new Document(UUID.randomUUID(), "", newPath, "jpg", new Node());
            newDoc = OKMDocument.INSTANCE.create(null, newDoc, fis);

            log.debug("newDoc: {}, {}", newPath, newDoc);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return ResponseEntity.ok("upload success");
    }
}
