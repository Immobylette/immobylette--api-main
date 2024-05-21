package com.immobylette.api.main.mapper;

import com.immobylette.api.main.dto.*;
import com.immobylette.api.main.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ElementMapper {

    @Mappings({
            @Mapping(target = "id", source = "element.id"),
            @Mapping(target = "description", source = "element.description"),
            @Mapping(target = "photo", source = "photoDto", qualifiedByName = "photoUrl"),
            @Mapping(target = "basePhotos", source = "folder", qualifiedByName = "photosBaseFolder"),
            @Mapping(target = "previousPhotos", source = "stepReceivedDto", qualifiedByName = "photoPreviousFolder"),
            @Mapping(target = "type", source = "element.elementType", qualifiedByName = "elementType"),
            @Mapping(target = "stepReceived", source = "stepReceivedDto"),
            @Mapping(target = "stepSent", source = "stepSentDto")
    })
    ElementDto fromElement(
            Element element,
            PhotoDto photoDto,
            FolderDto folder,
            StepReceivedDto stepReceivedDto,
            StepSentDto stepSentDto
            );

    @Named("photosBaseFolder")
    static List<String> photosBaseFolder(FolderDto folder) {
        return folder.getPhotos().stream()
                .map(PhotoDto::getUrl)
                .collect(Collectors.toList());
    }

    @Named("photoPreviousFolder")
    static List<String> photoPreviousFolder(StepReceivedDto stepReceivedDto) {
        return stepReceivedDto.getPhotos()
                .stream()
                .map(PhotoDto::getUrl)
                .toList();
    }

    @Named("elementType")
    static String elementType(ElementType elementType) {
        return elementType.getLabel();
    }

    @Named("photoUrl")
    static String photoUrl(PhotoDto photoDto) {
        return photoDto.getUrl();
    }

}
