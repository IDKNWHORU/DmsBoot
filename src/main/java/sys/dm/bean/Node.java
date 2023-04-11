package sys.dm.bean;

import java.util.HashSet;
import java.util.Set;

public class Node {
    public static final String AUTHOR = "okm:author";
    public static final String NAME = "okm:name";
    protected String path;

    protected Set<String> keywords = new HashSet<>();

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Set<String> getKeywords() {
        return keywords;
    }

    public Node addKeyword(String keyword) {
        keywords.add(keyword);
        return this;
    }
}
