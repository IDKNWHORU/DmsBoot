package sys.dm.repository;

import sys.dm.bean.NodeDocumentVersion;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface NodeDocumentVersionRepository extends CrudRepository<NodeDocumentVersion, UUID> {

}
