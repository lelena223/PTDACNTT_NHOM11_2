package com.example.btl_ttcsn.dto.response.project;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectCreateResponseDTO {
    private Long id;
    private String name;
    private String material;
    private Double budget;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startday;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deadline;
    private String status;
}
