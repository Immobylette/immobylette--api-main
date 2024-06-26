package com.immobylette.api.main.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

import java.util.UUID;

@Entity
@Getter
@Table(name = "heating_types")
public class HeatingType {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "label", nullable = false)
    private String label;

}
