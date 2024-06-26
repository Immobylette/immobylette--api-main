package com.immobylette.api.main.repository;

import com.immobylette.api.main.domain.InventoryStateLabel;
import com.immobylette.api.main.entity.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface StepRepository extends JpaRepository<Step, UUID> {

    @Query(value = "SELECT i.id as inventoryId, st.label as stateLabel " +
            "FROM Step s " +
            "JOIN s.stateType st " +
            "JOIN s.inventory i " +
            "WHERE s.element.id = :elementId " +
            "ORDER BY i.inventoryDate DESC" +
            " LIMIT 1")
    InventoryStateLabel findLabelStateByElementId(UUID elementId);


    @Query("SELECT s " +
            "FROM Step s " +
            "JOIN s.inventory i " +
            "JOIN s.element e " +
            "WHERE s.element.id = :elementId " +
            "ORDER BY i.inventoryDate DESC " +
            "LIMIT 2")
    List<Step> findLastTwoStepsByElementId(UUID elementId);

    int countByInventoryId(UUID inventoryId);

    Step findFirstByElementIdOrderByInventoryInventoryDateDesc(UUID elementId);

}
