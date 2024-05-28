package com.immobylette.api.main.repository;

import com.immobylette.api.main.entity.SignatureInventoryThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SignatureInventoryThirdPartyRepository extends JpaRepository<SignatureInventoryThirdParty, UUID>{
}
