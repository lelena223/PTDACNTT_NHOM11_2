package com.example.btl_ttcsn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String material;
    private Double budget;
    private String description;
    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startday;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deadline;
    @OneToMany(mappedBy = "project",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<User> users=new ArrayList<>();

    @OneToOne(mappedBy = "project",cascade = CascadeType.ALL)
    private Location location;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "project_parent")
    private Project projectParent;

    @OneToMany(mappedBy = "projectParent",cascade = CascadeType.ALL)
    private List<Project> projects=new ArrayList<>();

}
