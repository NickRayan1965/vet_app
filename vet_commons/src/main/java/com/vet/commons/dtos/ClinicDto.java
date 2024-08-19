package com.vet.commons.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClinicDto {
    
    private String id;
    
    private String name;

    private String address;

    private String city;

    private String state;

    private String country;

    private String postalCode;

    private String phoneNumber;
    
    private String email;

    private Boolean enabled;
}
