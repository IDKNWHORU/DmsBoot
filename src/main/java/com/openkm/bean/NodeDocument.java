package com.openkm.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NodeDocument {
    @Id
    private UUID uuid;
    private String context;
    private UUID parent;
    private String author;
    private String name;
    private String title;
    private String mimeType;
    private Calendar created;
    private Calendar lastModified;
    private String path;

    @Builder
    public NodeDocument(String context, UUID parent, String author, String name, String title, String mimeType, Calendar created, Calendar lastModified, String path) {
        this.context = context;
        this.parent = parent;
        this.author = author;
        this.name = name;
        this.title = title;
        this.mimeType = mimeType;
        this.created = created;
        this.lastModified = lastModified;
        this.path = path;
    }
}
