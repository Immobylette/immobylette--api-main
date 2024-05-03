package com.immobylette.api.main.repository;

import com.immobylette.api.main.domain.PropertyDistance;
import com.immobylette.api.main.entity.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;


public interface PropertyRepository extends JpaRepository<Property, UUID> {
    @Query(value = "SELECT p as property, calculate_distance(a.latitude, a.longitude, :latitude, :longitude) as distance " +
            "FROM Property p " +
            "JOIN p.address a " +
            "ORDER BY calculate_distance(a.latitude, a.longitude, :latitude, :longitude) ASC")
    Page<PropertyDistance> findAllOrderByDistance(double latitude, double longitude, Pageable pageable);

    @Query(
        "SELECT p " +
        "FROM Lease l " +
        "JOIN l.property p " +
        "JOIN l.inventories i " +
        "WHERE i.id = :id"
    )
    Property findByInventoryId(UUID id);
}
