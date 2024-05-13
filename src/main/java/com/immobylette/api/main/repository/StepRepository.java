package com.immobylette.api.main.repository;

import com.immobylette.api.main.entity.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface StepRepository extends JpaRepository<Step, UUID> {

    @Query("SELECT st.label " +
            "FROM Step s " +
            "JOIN s.stateType st " +
            "JOIN s.inventory i " +
            "WHERE s.element.id = :elementId " +
            "ORDER BY i.inventoryDate DESC" +
            " LIMIT 1")
    String findLabelStateByElementId(UUID elementId);
}
