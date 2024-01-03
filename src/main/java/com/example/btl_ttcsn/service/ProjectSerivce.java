package com.example.btl_ttcsn.service;

import com.example.btl_ttcsn.dto.request.project.ProjectCreateRequestDTO;
import com.example.btl_ttcsn.dto.response.project.ProjectCreateResponseDTO;
import com.example.btl_ttcsn.dto.response.project.ProjectDetailResponseDTO;
import com.example.btl_ttcsn.dto.response.project.ProjectParentsResponseDTO;

import java.util.List;

public interface ProjectSerivce  {
    ProjectCreateResponseDTO create(ProjectCreateRequestDTO projectCreateRequestDTO);
    ProjectParentsResponseDTO addProject(Long idParents,Long idChild);
    ProjectCreateResponseDTO update(ProjectCreateResponseDTO projectCreateResponseDTO);
    void remove(Long id);
    List<ProjectCreateResponseDTO> findAll();
    ProjectParentsResponseDTO findById(Long id);
    ProjectDetailResponseDTO addUser(Long idProject, Long userId);


}
