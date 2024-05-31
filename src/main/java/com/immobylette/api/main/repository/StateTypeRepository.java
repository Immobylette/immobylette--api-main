package com.immobylette.api.main.repository;

import com.immobylette.api.main.entity.StateType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StateTypeRepository extends JpaRepository<StateType, UUID> {
    StateType findByLabel(String label);
}
