package com.immobylette.api.main.entity;


import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "element_types")
public class ElementType {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "label")
    private String label;

    @ManyToMany
    @JoinTable(
            name = "element_attributes_element_types",
            joinColumns = @JoinColumn(name = "id_element_attribute"),
            inverseJoinColumns = @JoinColumn(name = "id_element_type")
    )
    private List<ElementAttribute> attributes;

}