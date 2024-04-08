package com.immobylette.api.main.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "third_parties")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "inventory_date")
    private String inventoryDate;

    @JoinColumn(name = "fk_inventory_type")
    @ManyToOne
    private InventoryType fkInventoryType;

    @JoinColumn(name = "fk_lease")
    @ManyToOne
    private Lease fkLease;

    @JoinColumn(name = "fk_third_party")
    @ManyToOne
    private ThirdParty agent;

    @ManyToMany
    @JoinTable(
            name = "signatures_inventories_third_parties",
            joinColumns = @JoinColumn(name = "id_inventory"),
            inverseJoinColumns = @JoinColumn(name = "id_third_party")
    )
    private List<ThirdParty> signatures;


}
