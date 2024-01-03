package com.example.btl_ttcsn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private String image;
    private double lat;
    private double lng;
    private String name;
    private String phone;
    private String region;
    @OneToOne
    @JoinColumn(name = "project_id")
    @JsonIgnore
    private Project project;
}
