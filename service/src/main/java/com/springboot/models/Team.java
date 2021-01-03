//package com.springboot.models;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//
//import javax.persistence.*;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Size;
//import java.sql.Date;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Set;
//
//@Entity
//@Table(	name = "teams",
//        uniqueConstraints = {
//                @UniqueConstraint(columnNames = "name")
//        })
//public class Team {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @NotBlank
//    @Size(max = 50)
//    private String name;
//
//    @NotBlank
//    @Size(max = 50)
//    private String location;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "manager_id", referencedColumnName = "id")
//    private User manager;
//
//    @OneToMany(cascade =CascadeType.ALL, mappedBy = "team")
//    @JsonBackReference
//    private ArrayList<User> players;
//
//
//    public Team() {
//    }
//
//    public Team(String name, String location) {
//        this.name = name;
//        this.location = location;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getLocation() {
//        return location;
//    }
//
//    public void setLocation(String location) {
//        this.location = location;
//    }
//}
