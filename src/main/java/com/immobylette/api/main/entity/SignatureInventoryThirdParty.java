package com.immobylette.api.main.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Table(name = "signatures_inventories_third_parties")
@IdClass(SignatureInventoryThirdPartyId.class)
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