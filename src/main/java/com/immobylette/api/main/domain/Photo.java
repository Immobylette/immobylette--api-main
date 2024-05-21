package com.immobylette.api.main.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class Photo {
    String description;

    MultipartFile file;
}
