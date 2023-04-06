package com.openkm.repository;

import com.openkm.bean.NodeBase;
import com.openkm.core.DatabaseException;
import com.openkm.core.PathNotFoundException;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface NodeBaseRepository extends CrudRepository<NodeBase, UUID> {
    public UUID getUuidFromPath(String path) throws PathNotFoundException, DatabaseException;
}
