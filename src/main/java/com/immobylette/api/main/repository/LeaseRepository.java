package com.immobylette.api.main.repository;

import com.immobylette.api.main.entity.Lease;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LeaseRepository extends JpaRepository<Lease, UUID> {
    Optional<Lease> findFirstByPropertyIdOrderByRentalStartDateDesc(UUID propertyId);
}
