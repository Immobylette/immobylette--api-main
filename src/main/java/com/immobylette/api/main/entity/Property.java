package com.immobylette.api.main.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "properties")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Property {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

    @Column(name = "ref_photo")
    private UUID refPhoto;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "bank_details")
    private String bankDetails;

    @Column(name = "social_security_number")
    private String socialSecurityNumber;

    @JoinColumn(name = "fk_third_party_type")
    @ManyToOne
    private ThirdPartyType thirdPartyType;

    @JoinColumn(name = "fk_address")
    @ManyToOne
    private Address address;

    @JoinColumn(name = "fk__third_party_owner")
    @ManyToOne
    private ThirdParty owner;

    @JoinColumn(name = "fk_hot_water_type")
    @ManyToOne
    private HotWaterType hotWaterType;

    @JoinColumn(name = "fk_heating_type")
    @ManyToOne
    private HeatingType heatingType;

    @JoinColumn(name = "fk_property_type")
    @ManyToOne
    private PropertyType propertyType;

    @JoinColumn(name = "fk_property_class")
    @ManyToOne
    private PropertyClass propertyClass;


}
