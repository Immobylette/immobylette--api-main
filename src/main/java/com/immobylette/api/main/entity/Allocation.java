package com.immobylette.api.main.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "allocations")
public class Allocation {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "label", nullable = false)
    private String label;

}
