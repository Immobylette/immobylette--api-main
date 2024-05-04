package com.immobylette.api.main.repository;

import com.immobylette.api.main.entity.Lease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface LeaseRepository extends JpaRepository<Lease, UUID> {
    @Query(
            "SELECT l " +
            "FROM Lease l " +
            "WHERE l.property.id = :propertyId " +
            "ORDER BY l.rentalStartDate DESC " +
            "LIMIT 1"
    )
    Optional<Lease> getLastLeaseByPropertyId(UUID propertyId);
}
