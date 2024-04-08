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
public class Address {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "number")
    private Integer number;

    @Column(name = "street")
    private String street;

    @Column(name = "zip")
    private Integer zip;

    @Column(name = "city")
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
