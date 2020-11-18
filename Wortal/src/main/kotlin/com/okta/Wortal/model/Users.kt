package com.okta.Wortal.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import javax.persistence.*

@Entity
@Table(name = "users")
class Users(
        @Column(name = "name")
        var name: String,
        @Column(name = "lastname")
        var lastname: String,
        @Column(name = "team")
        var team: String,
        @Column(name = "age")
        var age: String,
        @Column(name = "login", unique = true)
        var login: String,
        @Column(name = "password")
        var password: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    @SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", allocationSize = 1)
    @Column(name = "id")
    val id: Int = -1

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "lab_id")
//    @JsonManagedReference
//    var laboratory: Laboratory?=null

//    @OneToMany(cascade = [(CascadeType.ALL)], mappedBy = "reporter")
//    @JsonBackReference
//    val reporterIssues: List<Issue>? = null
//
//    @OneToMany(cascade = [(CascadeType.ALL)], mappedBy = "solver")
//    @JsonBackReference
//    val solverIssues: List<Issue>? = null
}