package com.immobylette.api.main.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "steps")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Step {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "error_description", nullable = false)
    private String errorDescription;

    @Column(name = "ref_photos_folder", nullable = false)
    private UUID refPhotosFolder;

    @JoinColumn(name = "fk_state_type", nullable = false)
    @ManyToOne
    private StateType stateType;

    @JoinColumn(name = "fk_inventory", nullable = false)
    @ManyToOne
    private Inventory inventory;

    @JoinColumn(name = "fk_element", nullable = false)
    @ManyToOne
    private Element element;
}
