package com.example.btl_ttcsn.controller;

import com.example.btl_ttcsn.base.VsResponseUtil;
import com.example.btl_ttcsn.dto.request.location.LocationRequestDTO;
import com.example.btl_ttcsn.dto.response.location.LocationResponseDTO;
import com.example.btl_ttcsn.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("location")
public class LocationController {
    @Autowired
    private LocationService locationService;
    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody LocationRequestDTO locationCreateRequestDTO){
        return ResponseEntity.ok(VsResponseUtil.success(locationService.create(locationCreateRequestDTO)));
    }
    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody LocationResponseDTO locationResponseDTO){
        return ResponseEntity.ok(VsResponseUtil.success(locationService.update(locationResponseDTO)));
    }
    @GetMapping("/")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(VsResponseUtil.success(locationService.findAll()));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        locationService.remove(id);
        return ResponseEntity.ok(VsResponseUtil.success("xóa thành công!"));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(VsResponseUtil.success(locationService.findById(id)));
    }
    @PostMapping("/{idLocation}/{idProject}")
    public ResponseEntity<?> addProject(@PathVariable Long idLocation,@PathVariable Long idProject){
        return ResponseEntity.ok(VsResponseUtil.success(locationService.addProject(idLocation,idProject)));
    }
}
