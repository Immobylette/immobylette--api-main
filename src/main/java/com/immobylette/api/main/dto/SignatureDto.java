package com.immobylette.api.main.dto;


import com.immobylette.api.main.domain.SignatureTypeEnum;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignatureDto {
    @NotNull
    private SignatureTypeEnum type;
}
