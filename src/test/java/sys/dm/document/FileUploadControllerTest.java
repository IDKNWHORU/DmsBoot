package sys.dm.document;

import sys.dm.controller.FileUploadController;
import sys.dm.core.FileSizeExceededException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockHttpServletRequest;

public class FileUploadControllerTest {
    private static final Logger log = LoggerFactory.getLogger(FileUploadControllerTest.class);

    public static void main(String... args) {
        FileUploadController fc = new FileUploadController(null);
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setAttribute("path", "okm:root");
        request.setAttribute("data", "aGVsbG8gd29ybGQ=");

        try {
            log.debug("Response is {}", fc.post(request));
        } catch (Exception | FileSizeExceededException e) {
            e.printStackTrace();
        }

    }
}
