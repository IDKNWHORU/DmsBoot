package com.openkm.document;

import com.openkm.controller.FileUploadController;
import org.springframework.mock.web.MockHttpServletRequest;

public class FileUploadControllerTest {

    public static void main(String... args) {
        FileUploadController fc = new FileUploadController();
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setAttribute("path", "okm:root");
        request.setAttribute("data", "aGVsbG8gd29ybGQ=");
        request.addParameter("action", "3");

        try {
            System.out.println(fc.post(request));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
