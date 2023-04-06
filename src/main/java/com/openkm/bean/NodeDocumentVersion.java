package com.openkm.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.util.UUID;

@Entity
@Getter
public class NodeDocumentVersion {
    @Id
    private UUID uuid;
}
