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
public class Lease {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "rental_start_date")
    private Date rentalStartDate;

    @Column(name = "nb_keys")
    private Integer nbKeys;

    @Column(name = "end_date")
    private Date endDate;

    @JoinColumn(name = "fk_owner")
    @ManyToOne
    private ThirdParty owner;

    @JoinColumn(name = "fk_tenant")
    @ManyToOne
    private ThirdParty tenant;

    @JoinColumn(name = "fk_property")
    @ManyToOne
    private Property property;
}
