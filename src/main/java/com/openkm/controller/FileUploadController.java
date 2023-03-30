package com.openkm.controller;

import com.openkm.api.OKMDocument;
import com.openkm.bean.Document;
import com.openkm.bean.FileUploadResponse;
import com.openkm.core.AutoClosableTempFile;
import com.openkm.core.EnumurationToIterator;
import com.openkm.core.MimeTypeConfig;
import com.openkm.frontend.UIFileUploadAction;
import com.openkm.util.FilenameUtil;
import com.openkm.util.FormatUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;


public class FileUploadController {
    private static final Logger log = LoggerFactory.getLogger(FileUploadController.class);

    public ResponseEntity<String> post(HttpServletRequest request) throws IOException {
        log.debug("doPost({})", request);

        FileUploadResponse fuResponse = new FileUploadResponse(new ArrayList<>(), new ArrayList<>(), false, false, false, false, "", "");
        try (AutoClosableTempFile tempFileWrapper = new AutoClosableTempFile("okm", ".tmp");
             FileOutputStream fos = new FileOutputStream(tempFileWrapper.getFile());
             BufferedOutputStream bos = new BufferedOutputStream(fos);
             FileInputStream fis = new FileInputStream(tempFileWrapper.getFile())) {

            if (request instanceof MultipartHttpServletRequest multipartRequest) {
                MultipartFile file = multipartRequest.getFile("file");
                String path = null;
                int action = 0;

                Iterator<String> it = new EnumurationToIterator<>(multipartRequest.getParameterNames());

                while (it.hasNext()) {
                    String paramName = it.next();
                    String paramValue = multipartRequest.getParameter(paramName);

                    if (paramName.equals("path")) {
                        path = paramValue;
                    } else if (paramName.equals("action")) {
                        action = Integer.parseInt(paramValue);
                    }
                }

                String fileName = null;
                InputStream is = null;
                long size = 0;
                if (file != null) {
                    fileName = file.getOriginalFilename();
                    is = file.getInputStream();
                    size = file.getSize();
                }

                log.debug("Filename: '{}'", fileName);
                // rename file to avoid problems with special characters

                if (action == UIFileUploadAction.INSERT) {
                    if (fileName != null & !fileName.isEmpty()) {
                        fileName = FilenameUtil.getName(fileName);
                        log.debug("Upload file '{}' into '{} ({})'", fileName, path, FormatUtil.formatSize(size));
                        String mimeType = MimeTypeConfig.MIME_TYPES.getContentType(fileName.toLowerCase());
                        Document doc = new Document("");
                        doc.setPath(path + "/" + fileName);

//                      log.debug("Wizard: {}", fuResponse);
//                      doc = new DbDocumentModule create
                        // fuResponse setPath
                        String UploadedUuid = doc.getUuid();
//                      log.debug("Wizard: {}", fuResponse);
                    }
                } else if (action == UIFileUploadAction.UPDATE) {
                    log.debug("File updated: {}", path);
                }
            } else {
                int action = request.getParameter("action") != null ? Integer.parseInt(request.getParameter("action")) : -1;
                if (action == UIFileUploadAction.DIGITAL_SIGNATURE_INSERT
                        || action == UIFileUploadAction.DIGITAL_SIGNATURE_UPDATE) {
                    String path = request.getAttribute("path").toString();
                    String data = request.getAttribute("data").toString();
                    bos.write(Base64.getDecoder().decode(data));
                    bos.flush();
                    fos.flush();

                    switch (action) {
                        case UIFileUploadAction.DIGITAL_SIGNATURE_INSERT -> {
                            Document newDoc = new Document("");
                            String newPath = path.substring(0, path.lastIndexOf(".") + 1) + "pdf";
                            newDoc.setPath(newPath);
                            newDoc = OKMDocument.getInstance().create(null, newDoc, fis);

                            log.debug("newDoc: {}, {}", newPath, newDoc);
                        }
                        case UIFileUploadAction.DIGITAL_SIGNATURE_UPDATE -> {

                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return ResponseEntity.ok("upload success");
    }
}
