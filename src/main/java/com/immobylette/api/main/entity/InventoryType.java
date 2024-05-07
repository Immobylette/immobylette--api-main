package com.immobylette.api.main.entity;


import com.immobylette.api.main.entity.enums.InventoryTypeLabel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

import java.util.UUID;

@Entity
@Table(name = "inventory_types")
public class InventoryType {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "label", nullable = false)
    @Enumerated(EnumType.STRING)
    private InventoryTypeLabel label;

}
