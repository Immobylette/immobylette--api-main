package com.immobylette.api.main.mapper;

import com.immobylette.api.main.dto.RoomDto;
import com.immobylette.api.main.entity.Allocation;
import com.immobylette.api.main.entity.Room;
import com.immobylette.api.main.entity.RoomType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    @Mappings({
            @Mapping(target = "roomType", source = "roomType", qualifiedByName = "roomType"),
            @Mapping(target = "allocation", source = "allocation", qualifiedByName = "allocation"),
            @Mapping(target = "nbOrder", source = "numberOrder")
    })
    RoomDto fromRoom(Room room);

    @Named("allocation")
    static String allocation(Allocation allocation) {
        return allocation.getLabel();
    }

    @Named("roomType")
    static String roomType(RoomType roomType) {
        return roomType.getLabel();
    }
}
