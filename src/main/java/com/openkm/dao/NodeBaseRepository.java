package com.openkm.dao;

import com.openkm.bean.NodeBase;
import com.openkm.core.DatabaseException;
import com.openkm.core.PathNotFoundException;
import org.springframework.data.repository.CrudRepository;

public interface NodeBaseRepository extends CrudRepository<NodeBase, String> {
    public String getUuidFromPath(String path) throws PathNotFoundException, DatabaseException;
}
