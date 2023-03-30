package com.openkm.bean;

import java.util.Set;

public record Document (
        String title
){
    private static final Node node = new Node();

    public void setPath(String path) {
        node.setPath(path);
    }

    public String getPath() {
        return node.getPath();
    }

    public Set<String> getKeywords() {
        return node.getKeywords();
    }

    @Override
    public String toString(){
        String serializedEntity = """
                Document{title='%s', path='%s', keywords=%s}
                """;

        return String.format(serializedEntity, title, node.getPath(), node.getKeywords());
    }

    public String getUuid() {
        return "";
    }
}
