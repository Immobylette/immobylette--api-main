package com.immobylette.api.main.entity;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "signatures_inventories_third_parties")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignatureLeaseThirdParty {

    @EmbeddedId
    private SignatureLeaseThirdPartyId id;


    @Column(name = "signature_date")
    private Date signatureDate;

}
