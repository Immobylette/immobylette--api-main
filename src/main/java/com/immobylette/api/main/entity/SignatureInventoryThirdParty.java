package com.immobylette.api.main.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "signatures_inventories_third_parties")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignatureInventoryThirdParty {

    @Id
    @JoinColumn(name = "id_inventory")
    @ManyToOne
    private Inventory inventory;

    @Id
    @JoinColumn(name = "id_third_party")
    @ManyToOne
    private ThirdParty thirdParty;

    @Column(name = "signature_date")
    private Date signatureDate;
}