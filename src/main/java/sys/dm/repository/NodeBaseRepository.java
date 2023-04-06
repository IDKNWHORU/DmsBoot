package sys.dm.repository;

import sys.dm.bean.NodeBase;
import sys.dm.core.DatabaseException;
import sys.dm.core.PathNotFoundException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface NodeBaseRepository extends CrudRepository<NodeBase, UUID> {

//    @Query("select nb.uuid from NodeBase nb where nb.parent = ?1 and nb.name = ?2")
//    public String getUuidFromPath(UUID uuid, String name) throws PathNotFoundException, DatabaseException;
}
