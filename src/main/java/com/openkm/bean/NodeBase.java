package com.openkm.bean;

import java.util.UUID;

public class NodeBase {

    public String getContext() {
        return "";
    }

    public UUID getUuid() {
        return UUID.randomUUID();
    }

    public String getPath() {
        return "";
    }
}
