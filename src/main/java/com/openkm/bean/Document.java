package com.openkm.bean;

import java.util.Set;

public record Document (
        String title
){
    private static final Node node = new Node();

    public void setPath(String path) {
        node.setPath(path);
    }

    public Set<String> getKeywords() {
        return node.getKeywords();
    }

    @Override
    public String toString(){
        return "Document{" +
                "title='" + title + '\'' +
                ", path='" + node.getPath() + '\'' +
                ", keywords=" + node.getKeywords() +
                '}';
    }
}
