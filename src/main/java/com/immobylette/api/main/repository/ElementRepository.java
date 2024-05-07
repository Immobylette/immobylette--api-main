package com.immobylette.api.main.repository;

import com.immobylette.api.main.entity.Element;
import com.immobylette.api.main.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ElementRepository extends JpaRepository<Element, UUID> {



    @Query("SELECT e FROM Element e WHERE e.room.id = :roomId")
    List<Element> findElementsByRoomId(UUID roomId);
}
