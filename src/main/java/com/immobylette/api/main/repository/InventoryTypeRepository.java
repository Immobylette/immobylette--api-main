package com.immobylette.api.main.repository;

import com.immobylette.api.main.entity.InventoryType;
import com.immobylette.api.main.entity.enums.InventoryTypeLabel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InventoryTypeRepository extends JpaRepository<InventoryType, UUID> {
    InventoryType findByLabel(InventoryTypeLabel label);
}
