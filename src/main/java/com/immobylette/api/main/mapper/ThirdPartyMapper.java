package com.immobylette.api.main.mapper;


import com.immobylette.api.main.dto.PhotoUrlDto;
import com.immobylette.api.main.dto.ThirdPartyDto;
import com.immobylette.api.main.entity.ThirdParty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ThirdPartyMapper {

    @Mappings({
        @Mapping(target = "id", source = "thirdParty.id"),
        @Mapping(target = "photo", source = "photo.url")
    })
    ThirdPartyDto fromThirdParty(ThirdParty thirdParty, PhotoUrlDto photo);

}


