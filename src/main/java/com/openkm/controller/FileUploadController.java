package com.openkm.controller;

import com.openkm.api.OKMDocument;
import com.openkm.bean.Document;
import com.openkm.bean.FileUploadResponse;
import com.openkm.core.FileSizeExceededException;
import com.openkm.core.MimeTypeConfig;
import com.openkm.repository.NodeBaseRepository;
import com.openkm.frontend.UIFileUploadAction;
import com.openkm.module.DocumentModule;
import com.openkm.util.AutoClosableTempFile;
import com.openkm.util.FilenameUtil;
import com.openkm.util.FormatUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Optional;

@RestController
public class FileUploadController {
    private static final Logger log = LoggerFactory.getLogger(FileUploadController.class);

    @PostMapping("/FileUpload")
    public ResponseEntity<String> post(HttpServletRequest request) throws IOException, FileSizeExceededException {
        log.debug("doPost({})", request);

        FileUploadResponse fuResponse = new FileUploadResponse(new ArrayList<>(), new ArrayList<>(), false, false, false, false, "", "");
        try (AutoClosableTempFile tempFileWrapper = new AutoClosableTempFile("okm", ".tmp");
             FileOutputStream fos = new FileOutputStream(tempFileWrapper.getFile());
             BufferedOutputStream bos = new BufferedOutputStream(fos);
             FileInputStream fis = new FileInputStream(tempFileWrapper.getFile())) {
            
            String path = request.getAttribute("path").toString();
            String data = request.getAttribute("data").toString();
            bos.write(Base64.getDecoder().decode(data));
            bos.flush();
            fos.flush();

            Document newDoc = new Document("");
            String newPath = path.substring(0, path.lastIndexOf(".") + 1) + "pdf";
            newDoc.setPath(newPath);
            newDoc = OKMDocument.INSTANCE.create(null, newDoc, fis);

            log.debug("newDoc: {}, {}", newPath, newDoc);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return ResponseEntity.ok("upload success");
    }
}
