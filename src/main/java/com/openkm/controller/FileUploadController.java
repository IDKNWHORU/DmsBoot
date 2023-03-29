package com.openkm.controller;

import com.openkm.api.OKMDocument;
import com.openkm.bean.AutoClosableTempFile;
import com.openkm.bean.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Base64;
import java.util.Map;


public class FileUploadController {
    private static final Logger log = LoggerFactory.getLogger(FileUploadController.class);

    public void post(Map<String, Object> request, String response) throws IOException {
        log.debug("doPost({}, {})", request, response);
        int action = 3;

        try (AutoClosableTempFile tempFileWrapper = new AutoClosableTempFile();
             FileOutputStream fos = new FileOutputStream(tempFileWrapper.getFile());
             BufferedOutputStream bos = new BufferedOutputStream(fos);
             FileInputStream fis = new FileInputStream(tempFileWrapper.getFile())) {

            if(action == 3 || action == 4) {
                String path = request.get("path").toString();
                String data = request.get("data").toString();
                bos.write(Base64.getDecoder().decode(data));
                bos.flush();
                fos.flush();

                switch (action) {
                    case 3:
                        Document newDoc = new Document("");
                        String newPath = path.substring(0, path.lastIndexOf(".") +1) + "pdf";
                        newDoc.setPath(newPath);
                        newDoc = OKMDocument.getInstance().create(null, newDoc, fis);

                        log.debug("newDoc: {}, {}", newPath, newDoc);
                        break;
                    case 4:
                        break;
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
