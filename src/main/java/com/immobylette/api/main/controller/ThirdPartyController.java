package com.immobylette.api.main.controller;

import com.immobylette.api.main.dto.ThirdPartyDto;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class AgentController {



    @GetMapping("/agents")
    public List<ThirdPartyDto> getAgents() {
        return null;
        }
    }
}
