package sys.dm.module;

import sys.dm.repository.NodeBaseRepository;

public class ModuleManager {
    private static DocumentModule documentModule;

    public static synchronized DocumentModule getDocumentModule() {
        if (documentModule == null) {
            NodeBaseRepository nodeBaseRepository = null;
            documentModule = new DocumentModule(nodeBaseRepository);
        }

        return documentModule;
    }
}
