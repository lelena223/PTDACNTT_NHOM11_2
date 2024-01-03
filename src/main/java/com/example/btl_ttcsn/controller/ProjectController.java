package com.example.btl_ttcsn.controller;

import com.example.btl_ttcsn.base.VsResponseUtil;
import com.example.btl_ttcsn.dto.request.project.ProjectCreateRequestDTO;
import com.example.btl_ttcsn.dto.response.project.ProjectCreateResponseDTO;
import com.example.btl_ttcsn.service.ProjectSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectSerivce projectSerivce;

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody ProjectCreateRequestDTO projectCreateRequestDTO){
        return ResponseEntity.ok(VsResponseUtil.success(projectSerivce.create(projectCreateRequestDTO)));
    }
    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody ProjectCreateResponseDTO projectCreateResponseDTO){
        return ResponseEntity.ok(VsResponseUtil.success(projectSerivce.update(projectCreateResponseDTO)));
    }
    @GetMapping("/")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(VsResponseUtil.success(projectSerivce.findAll()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(VsResponseUtil.success(projectSerivce.findById(id)));
    }
    @PostMapping("/{idParents}/{idChild}")
    public ResponseEntity<?> addProject(@PathVariable Long idParents,@PathVariable Long idChild){
        return ResponseEntity.ok(VsResponseUtil.success(projectSerivce.addProject(idParents,idChild)));
    }
    @PostMapping("user/{idProject}/{idUser}")
    public ResponseEntity<?> addUser(@PathVariable Long idProject,@PathVariable Long idUser){
        return ResponseEntity.ok(VsResponseUtil.success(projectSerivce.addUser(idProject,idUser)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        projectSerivce.remove(id);
        return ResponseEntity.ok(VsResponseUtil.success("xóa thành công!"));
    }
}
