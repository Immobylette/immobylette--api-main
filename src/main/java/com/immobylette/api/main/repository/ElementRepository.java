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
            "FROM Step s " +
            "JOIN s.element e " +
            "JOIN s.inventory i " +
            "WHERE e.id = :elementId " +
            "AND i.id = :inventoryId "
    )
    Optional<Element> findByInventoryId(UUID elementId, UUID inventoryId);

}
