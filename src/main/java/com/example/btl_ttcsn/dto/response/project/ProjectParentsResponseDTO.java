package com.example.btl_ttcsn.dto.response.project;

import com.example.btl_ttcsn.dto.common.UserDetailDTO;
import com.example.btl_ttcsn.dto.response.project.ProjectDetailResponseDTO;
import com.example.btl_ttcsn.entity.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectParentsResponseDTO {
    private Long id;
    private String name;
    private String material;
    private Double budget;
    private String description;
    private String status;
    private Date startday;
    private Date deadline;
    List<UserDetailDTO> people=new ArrayList<>();
    private Location location;
    private List<ProjectDetailResponseDTO> projects;
}
