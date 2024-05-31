package com.immobylette.api.main.mapper;

import com.immobylette.api.main.dto.ElementSummaryDto;
import com.immobylette.api.main.entity.Element;
import com.immobylette.api.main.entity.ElementType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ElementSummaryMapper {
    @Mappings({
            @Mapping(target = "type", source = "element.elementType", qualifiedByName = "ElementType"),
            @Mapping(target = "nbBasePhotos", source = "nbBasePhotos"),
            @Mapping(target = "nbPreviousPhotos", source = "nbPreviousPhotos"),
            @Mapping(target = "photo", source="element", qualifiedByName = "Photo")
    })
    ElementSummaryDto fromElement(Element element, int nbBasePhotos, int nbPreviousPhotos);

    @Named("ElementType")
    static String fromElementType(ElementType elementType) {
        return elementType.getLabel();
    }

    @Named("Photo")
    static String fromPhoto(Element element) {
        return String.format("%s/%s", element.getPhotoFolder().toString(), element.getPhoto().toString());
    }
}