package com.immobylette.api.main.controller;

import com.immobylette.api.main.dto.ThirdPartyDto;
import com.immobylette.api.main.exception.GCPStorageException;
import com.immobylette.api.main.exception.PhotoNotFoundException;
import com.immobylette.api.main.service.ThirdPartyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@AllArgsConstructor
public class ThirdPartyController {

    private final ThirdPartyService thirdPartyService;

    @GetMapping("/third-parties/agents")
    public List<ThirdPartyDto> getAgents() throws PhotoNotFoundException, GCPStorageException {
        return thirdPartyService.getAgents();
    }
}
