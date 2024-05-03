package com.immobylette.api.main.repository;

import com.immobylette.api.main.entity.Inventory;
import com.immobylette.api.main.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface InventoryRepository extends JpaRepository<Inventory, UUID> {
    @Query(
       "SELECT i.id " +
       "FROM Inventory i " +
       "JOIN i.lease l " +
       "LEFT JOIN i.signatures sitp " +
       "WHERE l.property.id = :propertyId " +
       "GROUP BY i.id " +
       "HAVING count(sitp.inventory.id) = 0"
    )
    UUID findCurrentInventoryByPropertyId(UUID propertyId);

    @Query(
        "SELECT r " +
        "FROM Room r " +
        "JOIN r.property p " +
        "WHERE p.id = :propertyId " +
        "AND calculate_nb_steps(r.id, :inventoryId) != calculate_nb_elements(r.id) " +
        "ORDER BY r.numberOrder " +
        "LIMIT 1"
    )
    Room findCurrentRoomByInventoryIdAndRoomId(UUID inventoryId, UUID propertyId);
}
