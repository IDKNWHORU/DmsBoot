package com.openkm.repository;

import com.openkm.bean.NodeDocument;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface NodeDocumentRepository extends CrudRepository<NodeDocument, UUID> {

}
