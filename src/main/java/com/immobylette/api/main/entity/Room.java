package com.immobylette.api.main.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "rooms")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "area", nullable = false)
    private Float area;

    @Column(name = "nb_walls", nullable = false)
    private Integer nbWalls;

    @Column(name = "nb_doors", nullable = false)
    private Integer nbDoors;

    @Column(name = "nb_windows", nullable = false)
    private Integer nbWindows;

    @Column(name = "reference")
    private String reference;

    @Column(name = "number_order", nullable = false)
    private Integer numberOrder;

    @JoinColumn(name = "fk_allocation", nullable = false)
    @ManyToOne
    private Allocation allocation;

    @JoinColumn(name = "fk_room_type", nullable = false)
    @ManyToOne
    private RoomType roomType;

    @JoinColumn(name = "fk_property", nullable = false)
    @ManyToOne
    private Property property;

    @OneToMany(mappedBy = "room")
    private List<Element> elements;

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
