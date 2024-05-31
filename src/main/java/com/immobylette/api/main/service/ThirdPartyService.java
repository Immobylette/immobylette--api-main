package com.immobylette.api.main.service;

import com.immobylette.api.main.domain.ThirdPartyTypeEnum;
import com.immobylette.api.main.dto.ThirdPartyDto;
import com.immobylette.api.main.entity.ThirdParty;
import com.immobylette.api.main.exception.GCPStorageException;
import com.immobylette.api.main.exception.PhotoNotFoundException;
import com.immobylette.api.main.mapper.ThirdPartyMapper;
import com.immobylette.api.main.repository.ThirdPartyRepository;
import com.immobylette.api.main.resource.PhotoResource;
import com.immobylette.api.main.dto.PhotoUrlDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class ThirdPartyService {

    private final ThirdPartyRepository thirdPartyRepository;
    private final PhotoResource photoResource;

    private final ThirdPartyMapper thirdPartyMapper;

    public List<ThirdPartyDto> getAgents() throws PhotoNotFoundException, GCPStorageException {
        List<ThirdParty> agents = thirdPartyRepository.findByThirdPartyTypeLabel(ThirdPartyTypeEnum.AGENT.getName());
        return agents.stream().map(agent -> {
            PhotoUrlDto photo = photoResource.getPhoto(agent.getRefPhoto());
            return thirdPartyMapper.fromThirdParty(agent, photo);
        }).toList();
    }
}
