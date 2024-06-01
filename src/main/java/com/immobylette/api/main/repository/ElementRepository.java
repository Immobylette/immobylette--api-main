package com.immobylette.api.main.repository;

import com.immobylette.api.main.entity.Element;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ElementRepository extends JpaRepository<Element, UUID> {

    @Query("SELECT e " +
            "FROM Element e " +
            "WHERE e.room.id = :roomId " +
            "AND e.elementType.label IN :walls "
    )
    List<Element> findWallsByRoomId(UUID roomId, List<String> walls);

    @Query("SELECT e " +
            "FROM Element e " +
            "WHERE e.room.id = :roomId " +
            "AND e.elementType.label NOT IN :walls "
    )
    List<Element> findElementsByRoomId(UUID roomId, List<String> walls);

    @Query("SELECT e " +
            "FROM Element e, Room r, Property p, Lease l, Inventory i " +
            "WHERE e.room.id = r.id " +
            "AND r.property.id = p.id " +
            "AND l.property.id = p.id " +
            "AND i.lease.id = l.id " +
            "AND e.id = :elementId " +
            "AND i.id = :inventoryId ")
    Optional<Element> findByInventoryId(UUID elementId, UUID inventoryId);

    @Query(
            "SELECT COUNT(*)" +
            "FROM Inventory i " +
            "JOIN i.lease l " +
            "JOIN l.property p " +
            "JOIN p.rooms r " +
            "JOIN r.elements e " +
            "WHERE i.id = :inventoryId "
    )
    int countByInventoryId(UUID inventoryId);

}
