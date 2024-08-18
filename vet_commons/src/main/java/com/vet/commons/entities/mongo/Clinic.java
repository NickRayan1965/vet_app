package com.vet.commons.entities.mongo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "clinics")
public class Clinic {
    @Id
    private String id;
    
    @Indexed(unique = true)
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
