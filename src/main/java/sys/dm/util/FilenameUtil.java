package sys.dm.util;

import java.io.File;

public class FilenameUtil {
    public static String getName(String filePath) {
        String fileName = "";
        int index = filePath.lastIndexOf(File.separator);
        if (index > 0 && index < filePath.length() - 1) {
            fileName = filePath.substring(index + 1);
        }
        return fileName;
    }
}
