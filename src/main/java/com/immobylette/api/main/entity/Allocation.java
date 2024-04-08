package com.immobylette.api.main.entity;


import io.hypersistence.utils.hibernate.type.basic.PostgreSQLHStoreType;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "third_party_types")
public class Room {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "description")
    private String description;

    @Column(name = "area")
    private Float area;

    @Column(name = "nb_walls")
    private Integer nbWalls;

    @Column(name = "nb_doors")
    private Integer nbDoors;

    @Column(name = "nb_windows")
    private Integer nbWindows;

    @Column(name = "reference")
    private String reference;

    @Column(name = "number_order")
    private Integer numberOrder;



}
