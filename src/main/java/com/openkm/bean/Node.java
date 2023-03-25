package com.openkm.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Node implements Serializable {
    private static final long serialVersionUID = 1L;
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

    public void setKeywords(Set<String> keywords) {
        this.keywords = keywords;
    }
}
