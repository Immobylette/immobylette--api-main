package com.immobylette.api.main.entity;


import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class SignatureLeaseThirdPartyId implements Serializable {


    @ManyToOne
    @JoinColumn(name = "id_third_party", nullable = false)
    private ThirdParty thirdParty;

    @ManyToOne
    @JoinColumn(name = "id_lease", nullable = false)
    private Lease lease;

}
