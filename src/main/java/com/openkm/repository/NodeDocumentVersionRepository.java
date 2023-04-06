package com.openkm.repository;

import com.openkm.bean.NodeDocumentVersion;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface NodeDocumentVersionRepository extends CrudRepository<NodeDocumentVersion, UUID> {

}
