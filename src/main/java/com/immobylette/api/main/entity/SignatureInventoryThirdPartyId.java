package com.immobylette.api.main.entity;


import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class SignatureInventoryThirdPartyId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "id_inventory", nullable = false)
    private Inventory inventory;

    @ManyToOne
    @JoinColumn(name = "id_third_party", nullable = false)
    private ThirdParty thirdParty;

}
