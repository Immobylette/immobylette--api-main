package com.immobylette.api.main.repository;


import com.immobylette.api.main.entity.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ThirdPartyRepository extends JpaRepository<ThirdParty, UUID> {

    List<ThirdParty> findByThirdPartyTypeLabel(String label);

    @Query("SELECT t " +
            "FROM Property p " +
            "JOIN p.leases l " +
            "JOIN l.tenant t " +
            "WHERE p.id = :id " +
            "ORDER BY l.endDate DESC " +
            "LIMIT 1")
    ThirdParty findCurrentTenantByPropertyId(UUID id);
}
