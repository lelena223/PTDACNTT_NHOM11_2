package com.example.btl_ttcsn.service.serviceImpl;

import com.example.btl_ttcsn.dto.request.location.LocationRequestDTO;
import com.example.btl_ttcsn.dto.response.location.LocationResponseDTO;
import com.example.btl_ttcsn.entity.Location;
import com.example.btl_ttcsn.entity.Project;
import com.example.btl_ttcsn.exception.NotFoundException;
import com.example.btl_ttcsn.repository.LocationRepository;
import com.example.btl_ttcsn.repository.ProjectRepository;
import com.example.btl_ttcsn.service.LocationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Override
    public LocationResponseDTO create(LocationRequestDTO locationCreateRequestDTO) {
        Location location=modelMapper.map(locationCreateRequestDTO,Location.class);
        locationRepository.save(location);
        return modelMapper.map(location, LocationResponseDTO.class);
    }

    @Override
    public LocationResponseDTO update(LocationResponseDTO locationResponseDTO) {
        Location location=modelMapper.map(locationResponseDTO,Location.class);
        Location lc=locationRepository.findById(location.getId()).get();
        if(lc==null){
            throw new NotFoundException("Not Found Location");
        }
        else{
            lc.setAddress(location.getAddress());
            lc.setImage(location.getImage());
            lc.setLat(location.getLat());
            lc.setLng(location.getLng());
            lc.setName(location.getName());
            lc.setPhone(location.getPhone());
            lc.setRegion(location.getRegion());
            System.out.println(lc.getId());
            locationRepository.save(lc);
            return modelMapper.map(lc, LocationResponseDTO.class);
        }
    }

    @Override
    public LocationResponseDTO findById(Long id) {
        Location location= locationRepository.findById(id).get();
        return modelMapper.map(location, LocationResponseDTO.class);
    }

    @Override
    public LocationResponseDTO addProject(Long idLocation, Long idProduct) {
        Location location=locationRepository.findById(idLocation).orElse(null);
        Project project=projectRepository.findById(idProduct).orElse(null);
        if(project==null){
            throw new NotFoundException("Not Found ID Project!");
        }
        if(location==null){
            throw new NotFoundException("Not Found ID Location");
        }
        location.setProject(project);
        locationRepository.save(location);
        return modelMapper.map(location, LocationResponseDTO.class);
    }

    @Override
    public void remove(Long id) {
        locationRepository.deleteById(id);
    }

    @Override
    public List<LocationResponseDTO> findAll() {
        List<Location> locations=locationRepository.findAll();
        List<LocationResponseDTO> list=new ArrayList<>();
        for(Location x:locations){
                list.add(modelMapper.map(x, LocationResponseDTO.class));
            }
        return list;
    }
}
