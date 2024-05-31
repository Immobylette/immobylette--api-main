package com.immobylette.api.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class PhotoDto {
    String description;

    MultipartFile file;
}
