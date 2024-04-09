package com.immobylette.api.main.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "signatures_leases_third_parties")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignatureLeaseThirdParty {

    @Id
    @JoinColumn(name = "id_lease")
    @ManyToOne
    private Lease lease;

    @Id
    @JoinColumn(name = "id_third_party")
    @ManyToOne
    private ThirdParty thirdParty;


    @Column(name = "signature_date")
    private Date signatureDate;

}
