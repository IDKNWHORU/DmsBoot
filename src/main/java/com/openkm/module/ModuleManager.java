package com.openkm.module;

public class ModuleManager {
    private static DocumentModule documentModule;

    public static synchronized DocumentModule getDocumentModule() {
        if(documentModule == null) {
            documentModule = new DocumentModule();
        }

        return documentModule;
    }
}
