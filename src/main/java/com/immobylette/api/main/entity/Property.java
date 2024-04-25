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
@Table(name = "properties")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Property {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "surface", nullable = false)
    private float surface;

    @Column(name = "nb_rooms", nullable = false)
    private Integer nbRooms;

    @Column(name = "ref_photo", nullable = false)
    private UUID photo;

    @Column(name = "ref_photos_folder")
    private UUID photoFolder;

    @JoinColumn(name = "fk_address", nullable = false)
    @ManyToOne
    private Address address;

    @JoinColumn(name = "fk_third_party_owner", nullable = false)
    @ManyToOne
    private ThirdParty owner;

    @JoinColumn(name = "fk_hot_water_type", nullable = false)
    @ManyToOne
    private HotWaterType hotWaterType;

    @JoinColumn(name = "fk_heating_type", nullable = false)
    @ManyToOne
    private HeatingType heatingType;

    @JoinColumn(name = "fk_property_type", nullable = false)
    @ManyToOne
    private PropertyType propertyType;

    @JoinColumn(name = "fk_property_class", nullable = false)
    @ManyToOne
    private PropertyClass propertyClass;

    @OneToMany(mappedBy = "property")
    private List<Lease> leases;


}
