package com.immobylette.api.main.mapper;

import com.immobylette.api.main.dto.*;
import com.immobylette.api.main.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ElementMapper {

    @Mappings({
            @Mapping(target = "id", source = "element.id"),
            @Mapping(target = "description", source = "element.description"),
            @Mapping(target = "photo", source = "photoUrlDto", qualifiedByName = "photoUrl"),
            @Mapping(target = "basePhotos", source = "folderBasePhotos.photos"),
            @Mapping(target = "previousPhotos", source = "folderPreviousPhotos", qualifiedByName = "photosPreviousFolder"),
            @Mapping(target = "type", source = "element.elementType", qualifiedByName = "elementType")
    })
    ElementDto fromElement(
            Element element,
            PhotoUrlDto photoUrlDto,
            FolderDto folderBasePhotos,
            FolderDto folderPreviousPhotos
            );

    @Named("photosPreviousFolder")
    static List<PhotoUrlDto> photosPreviousFolder(FolderDto folderPreviousPhotos) {
        if(folderPreviousPhotos != null){
            return folderPreviousPhotos.getPhotos();
        }
        else{
            return new ArrayList<>();
        }
    }

    @Named("elementType")
    static String elementType(ElementType elementType) {
        return elementType.getLabel();
    }

    @Named("photoUrl")
    static String photoUrl(PhotoUrlDto photoUrlDto) {
        return photoUrlDto.getUrl();
    }

}
