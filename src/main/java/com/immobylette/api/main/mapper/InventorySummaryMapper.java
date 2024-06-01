package com.immobylette.api.main.mapper;

import com.immobylette.api.main.dto.InventorySummaryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Date;

@Mapper(componentModel = "spring")
public interface InventorySummaryMapper {

    @Mappings({
        @Mapping(target = "nbRooms", source = "nbRooms"),
        @Mapping(target = "nbPhotos", source = "nbPhotos"),
        @Mapping(target = "date", source = "date")
    })
    InventorySummaryDto fromInventorySummary(int nbRooms, int nbPhotos, Date date);
}
