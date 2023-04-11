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
import org.springframework.web.util.HtmlUtils;

public class PathUtils {
    private static Logger log = LoggerFactory.getLogger(PathUtils.class);

    public static boolean checkPath(String path) {
        return true;
    }

    public static String getParent(String path) {
        log.debug("getParent({})", path);
        int lastSlash = path.lastIndexOf('/');
        String ret = (lastSlash > 0) ? path.substring(0, lastSlash) : "/";
        log.debug("getParent: {}", ret);
        return ret;
    }

    public static String getName(String path) {
        log.debug("getName({})", path);
        String ret = path.substring(path.lastIndexOf('/') + 1);
        log.debug("getName: {}", ret);
        return ret;
    }

    public static String escape(String name) {
        return HtmlUtils.htmlEscape(name.replaceAll("[/*\\s]+", " ").trim());
    }
}
