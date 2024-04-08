package com.immobylette.api.main.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "inventories")
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

    @OneToMany(mappedBy = "inventory")
    private List<SignatureInventoryThirdParty> signatures;
}
