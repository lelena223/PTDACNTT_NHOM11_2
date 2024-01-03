package com.example.btl_ttcsn.dto.response.location;

import com.example.btl_ttcsn.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationResponseDTO {
    private Long id;
    private String address;
    private String image;
    private double lat;
    private double lng;
    private String name;
    private String phone;
    private String region;
}
