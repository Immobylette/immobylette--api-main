package com.immobylette.api.main.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "signatures_inventories_third_parties")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignatureInventoryThirdParty {
    @Id
    @Column(name = "id")
    private UUID id;




}
