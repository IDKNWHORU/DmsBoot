package sys.dm.repository;

import sys.dm.bean.NodeDocument;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface NodeDocumentRepository extends CrudRepository<NodeDocument, UUID> {

}
