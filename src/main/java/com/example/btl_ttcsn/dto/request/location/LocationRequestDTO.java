package com.example.btl_ttcsn.dto.request.location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationRequestDTO {
    private String address;
    private String image;
    private double lat;
    private double lng;
    private String name;
    private String phone;
    private String region;
}
