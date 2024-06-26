package com.immobylette.api.main.entity;


import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "element_types")
@Getter
public class ElementType {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "label", nullable = false)
    private String label;

    @ManyToMany
    @JoinTable(
            name = "element_attributes_element_types",
            joinColumns = @JoinColumn(name = "id_element_attribute"),
            inverseJoinColumns = @JoinColumn(name = "id_element_type")
    )
    private List<ElementAttribute> attributes;
}