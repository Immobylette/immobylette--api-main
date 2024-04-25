package com.immobylette.api.main.service;

import com.immobylette.api.main.domain.ThirdPartyTypeEnum;
import com.immobylette.api.main.dto.ThirdPartyDto;
import com.immobylette.api.main.entity.ThirdParty;
import com.immobylette.api.main.mapper.ThirdPartyMapper;
import com.immobylette.api.main.repository.ThirdPartyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class ThirdPartyService {

    private final ThirdPartyRepository thirdPartyRepository;

    private final ThirdPartyMapper thirdPartyMapper;

    public List<ThirdPartyDto> getAgents() {
        List<ThirdParty> agents = thirdPartyRepository.findByThirdPartyTypeLabel(ThirdPartyTypeEnum.AGENT.getName());
        return agents.stream().map(thirdPartyMapper::fromThirdParty).toList();
    }
}
