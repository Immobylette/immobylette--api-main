package com.immobylette.api.main.repository;

import com.immobylette.api.main.entity.Inventory;
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
            "SELECT i.inventoryType.label " +
            "FROM Inventory i " +
            "JOIN Lease l ON l.id = i.lease.id AND l.property.id = :propertyId " +
            "ORDER BY i.inventoryDate DESC " +
            "LIMIT 1"
    )
    String findLastInventoryType(UUID propertyId);
}
