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

package sys.dm.api;

import sys.dm.bean.Document;
import sys.dm.module.DocumentModule;
import sys.dm.module.ModuleManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sys.dm.repository.NodeBaseRepository;

import java.io.InputStream;

public enum OKMDocument {
    INSTANCE;
    private static final Logger log = LoggerFactory.getLogger(OKMDocument.class);

    OKMDocument() {
    }

    public Document create(String token, Document doc, InputStream is, NodeBaseRepository nodeBaseRepository) {
        log.debug("create({}, {}, {})", token, doc, is);
        DocumentModule documentModule = ModuleManager.getDocumentModule(nodeBaseRepository);
        Document newDocument = documentModule.create(token, doc, is);
        log.debug("create: {}", newDocument);
        return newDocument;
    }
}
