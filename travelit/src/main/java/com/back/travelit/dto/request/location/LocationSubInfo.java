package com.back.travelit.dto.request.location;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationSubInfo {
    @NotBlank(message = "key 필요")
    private String infoKey;
    @NotBlank(message = "value 필요")
    private String infoValue;
}
