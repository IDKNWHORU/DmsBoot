/**
 * OpenKM, Open Document Management System (http://www.openkm.com)
 * Copyright (c) Paco Avila & Josep Llort
 * <p>
 * No bytes were intentionally harmed during the development of this application.
 * <p>
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package sys.dm.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Calendar;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
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
    public NodeDocument(UUID uuid, String context, UUID parent, String author, String name, String title, String mimeType, Calendar created, Calendar lastModified, String path) {
        this.uuid = uuid;
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
