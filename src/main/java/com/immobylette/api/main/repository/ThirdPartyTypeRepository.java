package com.immobylette.api.main.repository;


import com.immobylette.api.main.entity.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface  ThirdPartyTypeRepository extends JpaRepository<ThirdParty, UUID> {

    List<ThirdParty> findByThirdPartyTypeLabel(String label);
}
