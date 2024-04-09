package com.immobylette.api.main.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "addresses")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "number", nullable = false)
    private Integer number;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "zip", nullable = false)
    private Integer zip;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "floor")
    private Integer floor;

    @Column(name = "extra")
    private String extra;

    @Column(name = "latitude")
    private Float latitude;

    @Column(name = "longitude")
    private Float longitude;

}
