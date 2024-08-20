package com.vet.commons.dtos.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VeterinarianPerfilDto {
    private Long userId;

    private String licenceNumber;

    private String address;

    private String city;

    private String state;
    
    private String workHours;

    private Boolean enabled;

    private ClinicDto clinic;
}
