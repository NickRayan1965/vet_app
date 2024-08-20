package com.vet.commons.entities.mongo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "veterinarian_perfils")
public class VeterinarianPerfil {
    @Id
    private String id;

    @Indexed(unique = true)
    private Long userId;

    private String licenceNumber;

    private String address;

    private String city;

    private String state;
    
    private String workHours;

    private Boolean enabled;

    @DocumentReference
    private Clinic clinic;
}
