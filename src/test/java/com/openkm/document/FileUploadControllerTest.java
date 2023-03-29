package com.openkm.document;

import com.openkm.controller.FileUploadController;

import java.util.HashMap;
import java.util.Map;

public class FileUploadControllerTest {

    public static void main(String... args) {
        FileUploadController fc = new FileUploadController();
        Map request = new HashMap<>(
        ) {{
            put("path", "okm:root");
            put("data", "data");
        }};
        String response = "send response";

        try {
            fc.post(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
