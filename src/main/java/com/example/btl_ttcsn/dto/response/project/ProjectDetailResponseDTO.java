package com.example.btl_ttcsn.dto.response.project;

import com.example.btl_ttcsn.dto.common.UserDetailDTO;
import com.example.btl_ttcsn.entity.Location;
import com.example.btl_ttcsn.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDetailResponseDTO {
    private Long id;
    private String name;
    private String material;
    private Double budget;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startday;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deadline;
    private List<UserDetailDTO> users=new ArrayList<>();
    private Location location;
}
