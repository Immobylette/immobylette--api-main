package com.immobylette.api.main.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "inventories")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "inventory_date")
    private Date inventoryDate;

    @JoinColumn(name = "fk_inventory_type", nullable = false)
    @ManyToOne
    private InventoryType inventoryType;

    @JoinColumn(name = "fk_lease", nullable = false)
    @ManyToOne
    private Lease lease;

    @JoinColumn(name = "fk_third_party", nullable = false)
    @ManyToOne
    private ThirdParty agent;

    @OneToMany(mappedBy = "inventory")
    private List<SignatureInventoryThirdParty> signatures;

    @OneToMany(mappedBy = "inventory")
    private List<Step> steps;
}
