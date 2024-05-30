package com.immobylette.api.main.mapper;

import com.immobylette.api.main.dto.FolderDto;
import com.immobylette.api.main.dto.PhotoDto;
import com.immobylette.api.main.dto.StepReceivedDto;
import com.immobylette.api.main.entity.StateType;
import com.immobylette.api.main.entity.Step;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StepReceivedMapper {

    @Mappings({
            @Mapping(target = "photos", source = "folderPreviousPhoto", qualifiedByName = "photosPreviousFolder"),
            @Mapping(target = "state", source = "step.stateType", qualifiedByName = "stateTypeStep")
    })
    StepReceivedDto fromStep(Step step, FolderDto folderPreviousPhoto);


    @Named("photosPreviousFolder")
    static List<PhotoDto> photosPreviousFolder(FolderDto folderPreviousPhoto) {
        return folderPreviousPhoto.getPhotos();
    }

    @Named("stateTypeStep")
    static String stateTypeStep(StateType stateType) {
        return stateType.getLabel();
    }

}
