package com.immobylette.api.main.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class SignatureLeaseThirdPartyId implements Serializable {

    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_third_party")
    private ThirdParty thirdParty;

    @ManyToOne
    @JoinColumn(name = "id_lease")
    private Lease lease;

}
