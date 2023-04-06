package com.openkm.document;

import com.openkm.controller.FileUploadController;
import com.openkm.core.FileSizeExceededException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockHttpServletRequest;

public class FileUploadControllerTest {
    private static Logger log = LoggerFactory.getLogger(FileUploadControllerTest.class);

    public static void main(String... args) {
        FileUploadController fc = new FileUploadController();
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setAttribute("path", "okm:root");
        request.setAttribute("data", "aGVsbG8gd29ybGQ=");
        request.addParameter("action", "3");

        try {
            log.debug("Response is {}", fc.post(request));
        } catch (Exception | FileSizeExceededException e) {
            e.printStackTrace();
        }

    }
}
