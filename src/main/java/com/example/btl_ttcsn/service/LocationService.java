package com.example.btl_ttcsn.service;

import com.example.btl_ttcsn.dto.request.location.LocationRequestDTO;
import com.example.btl_ttcsn.dto.response.location.LocationResponseDTO;

import java.util.List;

public interface LocationService {
    LocationResponseDTO create(LocationRequestDTO locationCreateRequestDTO);
    LocationResponseDTO update(LocationResponseDTO locationResponseDTO);
    LocationResponseDTO findById(Long id);
    LocationResponseDTO addProject(Long idLocation, Long idProduct);
    void remove(Long id);
    List<LocationResponseDTO> findAll();

}
