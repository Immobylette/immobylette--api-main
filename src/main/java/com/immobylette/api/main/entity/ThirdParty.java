package com.immobylette.api.main.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "third_parties")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThirdParty {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "ref_photo")
    private UUID refPhoto;

    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

    @Column(name = "bank_details", nullable = false)
    private String bankDetails;

    @Column(name = "social_security_number", nullable = false)
    private String socialSecurityNumber;

    @JoinColumn(name = "fk_third_party_type", nullable = false)
    @ManyToOne
    private ThirdPartyType thirdPartyType;
}
