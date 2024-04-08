package com.immobylette.api.main.mapper;


import com.immobylette.api.main.dto.ThirdPartyDto;
import com.immobylette.api.main.entity.ThirdParty;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ThirdPartyMapper {

    ThirdPartyDto fromThirdParty(ThirdParty thirdParty);

}


