package com.openkm.util;

import java.io.File;

public class DocConverter {
    public static DocConverter getInstance() {
        return new DocConverter();
    }

    public boolean convertibleToPdf(String mimeType) {
        return true;
    }

    public void docToPdf(File file, String mimeType, File tmpPdf) {
    }
}
