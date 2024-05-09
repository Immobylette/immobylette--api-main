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

    @Mapping(target = "type", source = "elementType", qualifiedByName = "ElementType")
    ElementSummaryDto fromElement(Element element);

    @Named("ElementType")
    static String fromElementType(ElementType elementType) {
        return elementType.getLabel();
    }
}