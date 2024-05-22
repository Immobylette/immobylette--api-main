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
            @Mapping(target = "basePhotos", source = "folderBasePhotos", qualifiedByName = "photosBaseFolder"),
            @Mapping(target = "previousPhotos", source = "folderPreviousPhotos", qualifiedByName = "photosPreviousFolder"),
            @Mapping(target = "type", source = "element.elementType", qualifiedByName = "elementType"),
            @Mapping(target = "step", source = "stepReceivedDto")
    })
    ElementDto fromElement(
            Element element,
            PhotoDto photoDto,
            FolderDto folderBasePhotos,
            FolderDto folderPreviousPhotos,
            StepReceivedDto stepReceivedDto
            );

    @Named("photosBaseFolder")
    static List<String> photosBaseFolder(FolderDto folderBasePhotos) {
        return folderBasePhotos.getPhotos().stream()
                .map(PhotoDto::getUrl)
                .collect(Collectors.toList());
    }

    @Named("photosPreviousFolder")
    static List<String> photosPreviousFolder(FolderDto folderPreviousPhotos) {
        if(folderPreviousPhotos != null){
            return folderPreviousPhotos.getPhotos().stream()
                    .map(PhotoDto::getUrl)
                    .collect(Collectors.toList());
        }
        else{
            return null;
        }
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
