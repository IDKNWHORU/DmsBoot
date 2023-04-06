package com.openkm.repository;

import com.openkm.bean.NodeBase;
import com.openkm.core.DatabaseException;
import com.openkm.core.PathNotFoundException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface NodeBaseRepository extends CrudRepository<NodeBase, UUID> {

    @Query("select n.uuid from NodeBase n where n.path = ?1")
    public String getUuidFromPath(String path) throws PathNotFoundException, DatabaseException;
}
