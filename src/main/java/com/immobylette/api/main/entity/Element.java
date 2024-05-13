package com.immobylette.api.main.entity;


import io.hypersistence.utils.hibernate.type.basic.PostgreSQLHStoreType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "elements")
@Getter
@Setter
public class Element {

    @Id
    @Column(name = "id")
    private UUID id;

    @Type(PostgreSQLHStoreType.class)
    @Column(columnDefinition = "hstore", name = "attributes")
    private Map<String, String> attributes = Map.of();

    @Column(name = "ref_photo", nullable = false)
    private UUID photo;

    @Column(name = "ref_photos_folder", nullable = false)
    private UUID photoFolder;

    @Column(name = "description")
    private String description;

    @JoinColumn(name = "fk_parent_element")
    @ManyToOne
    private Element parent;

    @JoinColumn(name = "fk_element_type", nullable = false)
    @ManyToOne
    private ElementType elementType;

    @JoinColumn(name = "fk_room", nullable = false)
    @ManyToOne
    private Room room;

    @OneToMany(mappedBy = "element")
    private List<Step> steps;

}
