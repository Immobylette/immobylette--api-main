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
@Table(name = "leases")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lease {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "rental_start_date", nullable = false)
    private Date rentalStartDate;

    @Column(name = "nb_keys", nullable = false, columnDefinition = "integer default 1")
    private Integer nbKeys;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @JoinColumn(name = "fk_owner", nullable = false)
    @ManyToOne
    private ThirdParty owner;

    @JoinColumn(name = "fk_tenant", nullable = false)
    @ManyToOne
    private ThirdParty tenant;

    @JoinColumn(name = "fk_property", nullable = false)
    @ManyToOne
    private Property property;

    @OneToMany(mappedBy = "lease")
    private List<SignatureLeaseThirdParty> signatures;
}
