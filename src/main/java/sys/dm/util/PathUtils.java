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
