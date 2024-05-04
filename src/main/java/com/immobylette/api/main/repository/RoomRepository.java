package com.immobylette.api.main.repository;

import com.immobylette.api.main.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, UUID> {
    @Query(
        "SELECT r " +
        "FROM Room r " +
        "JOIN r.property p " +
        "WHERE p.id = :propertyId " +
        "AND calculate_nb_steps(r.id, :inventoryId) != calculate_nb_elements(r.id) " +
        "ORDER BY r.numberOrder " +
        "LIMIT 1"
    )
    Optional<Room> findCurrentRoomByInventoryIdAndRoomId(UUID inventoryId, UUID propertyId);
}
