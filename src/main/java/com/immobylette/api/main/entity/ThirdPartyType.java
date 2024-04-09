package com.immobylette.api.main.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "third_party_types")
public class ThirdPartyType {

    @Id
    private UUID id;

    @Column(name = "label", nullable = false)
    private String label;

}