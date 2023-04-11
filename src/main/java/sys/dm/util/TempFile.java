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

package sys.dm.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class TempFile implements AutoCloseable {
    private static final Logger log = LoggerFactory.getLogger(TempFile.class);

    private final File file;

    public TempFile(String prefix, String suffix) throws IOException {
        file = File.createTempFile(prefix, suffix);
        log.debug("TempFile.ctor(): file = {}", file);
    }

    public File getFile() {
        return file;
    }

    @Override
    public void close() {
        boolean isDeleted = file.delete();
        log.debug("TempFile.close(): file.delete() returned {}", isDeleted);
    }
}
