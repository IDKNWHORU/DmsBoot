package com.openkm.document;

import com.openkm.controller.FileUploadController;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.mock.web.MockHttpServletRequest;

public class FileUploadControllerTest {

    public static void main(String... args) {
        FileUploadController fc = new FileUploadController();
        HttpServletRequest request = new MockHttpServletRequest();
        request.setAttribute("path", "okm:root");
        request.setAttribute("data", "data");

        try {
            System.out.println(fc.post(request));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
