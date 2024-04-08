package com.immobylette.api.main.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "room_types")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomType {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "surname")
    private String label;
}
