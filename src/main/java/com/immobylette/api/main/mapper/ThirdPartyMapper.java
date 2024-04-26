package com.immobylette.api.main.mapper;


import com.immobylette.api.main.dto.ThirdPartyDto;
import com.immobylette.api.main.entity.ThirdParty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ThirdPartyMapper {

    @Mapping(target = "photo", source = "refPhoto")
    ThirdPartyDto fromThirdParty(ThirdParty thirdParty);

}


