package com.openkm.dao;

import com.openkm.bean.NodeBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum NodeBaseDAO {
    INSTANCE;
    private static Logger log = LoggerFactory.getLogger(NodeBaseDAO.class);

    private NodeBaseDAO() {
    }

    public String getUuidFromPath(String path) {
        return calculateUuidFromPath(path);
    }

    public NodeBase findByPK(String parentUuid) {
        return null;
    }

    private String calculateUuidFromPath(String path) {
        log.debug("calculateUuidFromPath({})", path);

        return "";
    }

}
