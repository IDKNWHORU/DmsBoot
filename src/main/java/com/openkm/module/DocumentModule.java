package com.openkm.module;

import com.openkm.bean.Document;
import com.openkm.bean.FileUploadResponse;
import com.openkm.bean.NodeBase;
import com.openkm.bean.NodeDocument;
import com.openkm.core.*;
import com.openkm.dao.MimeTypeDAO;
import com.openkm.dao.NodeBaseDAO;
import com.openkm.util.AutoClosableTempFile;
import com.openkm.util.FormatUtil;
import com.openkm.util.PathUtils;
import com.openkm.util.SystemProfiling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class DocumentModule {
    private static final Logger log = LoggerFactory.getLogger(DocumentModule.class);

    public Document create(String token, Document doc, java.io.InputStream is) {
        log.debug("create({}, {}, {})", token, doc, is);
        long begin = System.currentTimeMillis();
        Document newDocument = null;
        NodeDocument docNode = BaseDocumentModule.create(doc.title());

        newDocument = BaseDocumentModule.getProperties("", docNode);

        return newDocument;
    }

    public Document create(String token, Document doc, InputStream is, long size, String userId, FileUploadResponse fuResponse) throws Exception, FileSizeExceededException {
        return create(token, doc, is, size, userId, fuResponse, null);
    }

    public Document create(String token, Document doc, InputStream is, long size, String userId, FileUploadResponse fuResponse, String emailSubject) throws Exception, FileSizeExceededException {
        log.debug("create({}, {}, {}, {}, {}, {}, {})", token, doc, is, size, userId, fuResponse, emailSubject);
        long begin = System.currentTimeMillis();
        Document newDocument = null;

        if (!PathUtils.checkPath(doc.getPath())) {
            throw new RepositoryException("Invalid path: " + doc.getPath());
        }

        String parentPath = null;
        String name = null;

        // process emailSubject is null
//        if(emailSubject != null && !emailSubject.isEmpty()) {
//            String escapedSub
//            name = emailSubject;
//        } else {
//            name = doc.getTitle();
//        }
        if (parentPath == null || name == null) {
            parentPath = PathUtils.getParent(doc.getPath());
            name = PathUtils.getName(doc.getPath());
        }

        int extIdx = name.lastIndexOf(".");
        String fileExtension = extIdx > 0 ? name.substring(extIdx) : ".tmp";

        try (AutoClosableTempFile tempFileWrapper = new AutoClosableTempFile("okm", fileExtension);
             FileOutputStream fos = new FileOutputStream(tempFileWrapper.getFile());
             InputStream fis = new FileInputStream(tempFileWrapper.getFile())) {
            // token check

            if (Config.CLOUD_MAX_REPOSITORY_SIZE > 0) {
                // check repository size
            }

            if (Config.MAX_FILE_SIZE > 0 && size > Config.MAX_FILE_SIZE) {
                log.error("Uploaded file size: {} ({}), Max file size: {} ({})", FormatUtil.formatSize(size), size,
                        FormatUtil.formatSize(Config.MAX_FILE_SIZE), Config.MAX_FILE_SIZE);
//                String usr = userId
                throw new FileSizeExceededException(FormatUtil.formatSize(size));
            }

            name = PathUtils.escape(name);

            if (!name.isEmpty()) {
                doc.setPath(parentPath + "/" + name);

                String mimeType = MimeTypeConfig.mimeTypes.getContentType(name.toLowerCase());
                doc.setMimeType(mimeType);

                if (Config.RESTRICT_FILE_MIME && MimeTypeDAO.findByName(mimeType) == null) {
//                    String usr
                    throw new UnsupportedMimeTypeException(mimeType);
                }

                if (!Config.RESTRICT_FILE_NAME.isEmpty()) {

                }

                byte[] buff = new byte[4 * 1024];
                int read;

                while ((read = is.read(buff)) != -1) {
                    fos.write(buff, 0, read);
                }

                fos.flush();
                is.close();

                String parentUuid = NodeBaseDAO.INSTANCE.getUuidFromPath(parentPath);
                NodeBase parentNode = NodeBaseDAO.INSTANCE.findByPK(parentUuid);

                Set<String> keywords = Optional.ofNullable(doc.getKeywords()).orElseGet(HashSet::new);
                NodeDocument docNode = BaseDocumentModule.create(null, parentPath, parentNode, name, doc.title(),
                        doc.getCreated(), mimeType, is, size, keywords, new HashSet<>(), new HashSet<>(), new ArrayList<>(),
                        null, fuResponse);

                newDocument = BaseDocumentModule.getProperties("", docNode);

                if (fuResponse == null) {
                    fuResponse = new FileUploadResponse(new ArrayList<>(), new ArrayList<>(), false,
                            false, false, false,
                            "", "");
                }
            } else {
                throw new RepositoryException("Invalid document name");
            }
        }

        SystemProfiling.log(doc.getPath(), System.currentTimeMillis() - begin);
        log.trace("create.Time: {}", System.currentTimeMillis() - begin);
        log.debug("create: {}", newDocument);
        return new Document("");
    }
}
