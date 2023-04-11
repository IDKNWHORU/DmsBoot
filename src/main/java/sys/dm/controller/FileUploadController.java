/**
 * OpenKM, Open Document Management System (http://www.openkm.com)
 * Copyright (c) Paco Avila & Josep Llort
 * <p>
 * No bytes were intentionally harmed during the development of this application.
 * <p>
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package sys.dm.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import sys.dm.api.OKMDocument;
import sys.dm.bean.Document;
import sys.dm.bean.Node;
import sys.dm.core.FileSizeExceededException;
import sys.dm.util.TempFile;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

@RestController
public class FileUploadController {
    private static final Logger log = LoggerFactory.getLogger(FileUploadController.class);

    @PostMapping("/FileUpload")
    public ResponseEntity<String> post(HttpServletRequest request) throws IOException, FileSizeExceededException {
        log.debug("doPost({})", request);

        try (TempFile tempFileWrapper = new TempFile("okm", ".tmp");
             FileOutputStream fos = new FileOutputStream(tempFileWrapper.getFile());
             BufferedOutputStream bos = new BufferedOutputStream(fos);
             FileInputStream fis = new FileInputStream(tempFileWrapper.getFile())) {

            String data = request.getAttribute("data").toString();
            bos.write(Base64.getDecoder().decode(data));
            bos.flush();
            fos.flush();

            String path = request.getAttribute("path").toString();
            String newPath = path.substring(0, path.lastIndexOf(".") + 1) + "pdf";
            Document newDoc = new Document(UUID.randomUUID(), "", newPath, "jpg", new Node());
            newDoc = OKMDocument.INSTANCE.create(null, newDoc, fis);

            log.debug("newPath: {}, newDoc: {}", newPath, newDoc);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok("upload success");
    }
}
