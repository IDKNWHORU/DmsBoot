package sys.dm.bean;

import java.util.ArrayList;
import java.util.List;

public record FileUploadResponse(List<String> groupList, List<String> workflowList, boolean showWizardCategories,
                                 boolean showWizardKeywords, boolean digitalSignature, boolean hasAutomation,
                                 String error, String path) {

    public FileUploadResponse setError(String error) {
        return new FileUploadResponse(new ArrayList<>(), new ArrayList<>(), false, false, false, false, error, "");
    }

    public FileUploadResponse setPath(String path) {
        return new FileUploadResponse(new ArrayList<>(), new ArrayList<>(), false, false, false, false, "", path);
    }

    @Override
    public String toString() {
        String entity = """
                {
                                    "path": %s,
                                    "showWizardCategories": %s,
                                    "showWizardKeywords": %s,
                                    "groupList": %s,
                                    "workflowList": %s,
                                    "digitalSignature": %s,
                                    "hasAutomation": %s,
                                    "error": %s,
                                    "digitalSignature": %s
                                }
                                """;

        return String.format(entity, groupList, workflowList, showWizardCategories, showWizardKeywords, digitalSignature, hasAutomation, error, path, digitalSignature);
    }
}
