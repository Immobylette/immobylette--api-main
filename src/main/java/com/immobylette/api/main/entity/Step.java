package com.immobylette.api.main.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "leases")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Step {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "description")
    private String description;

    @Column(name = "error_description")
    private String errorDescription;

    @Column(name = "ref_photos_folder")
    private UUID refPhotosFolder;

    @JoinColumn(name = "fk_state_type")
    @ManyToOne
    private StateType stateType;

    @JoinColumn(name = "fk_inventory")
    @ManyToOne
    private InventoryType inventoryType;

    @JoinColumn(name = "fk_element")
    @ManyToOne
    private Element element;
}
