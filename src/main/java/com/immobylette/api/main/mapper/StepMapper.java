package com.immobylette.api.main.mapper;

import com.immobylette.api.main.dto.StepDto;
import com.immobylette.api.main.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface StepMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "refPhotosFolder", source = "refPhotosFolder"),
            @Mapping(target = "description", source = "step.description")
    })
    Step fromStepSentDto(StepDto step, StateType stateType, Inventory inventory, Element element, UUID refPhotosFolder);
}
