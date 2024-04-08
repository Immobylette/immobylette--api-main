package com.immobylette.api.main.entity;


import io.hypersistence.utils.hibernate.type.basic.PostgreSQLHStoreType;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "third_party_types")
public class Element {

    @Id
    @Column(name = "id")
    private UUID id;

    @Type(PostgreSQLHStoreType.class)
    @Column(columnDefinition = "hstore", name = "attributes")
    private Map<String, String> attributes = Map.of();

    @Column(name = "ref_photo")
    private String photo;

    @Column(name = "ref_photo_folder")
    private String photoFolder;

    @JoinColumn(name = "fk_element_parent")
    @ManyToOne
    private Element parent;

    @JoinColumn(name = "fk_element_type")
    @ManyToOne
    private ElementType fkElementType;

    @JoinColumn(name = "fk_room")
    @ManyToOne
    private Room fkRoom;

}
