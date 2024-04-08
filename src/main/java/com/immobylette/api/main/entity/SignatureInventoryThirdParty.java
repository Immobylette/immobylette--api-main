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

    @EmbeddedId
    private SignatureInventoryThirdPartyId id;


    @Column(name = "signature_date")
    private Date signatureDate;

}
