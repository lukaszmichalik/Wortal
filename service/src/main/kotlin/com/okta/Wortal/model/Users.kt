package com.okta.Wortal.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import java.sql.Date
import javax.persistence.*

@Entity
@Table(name = "users")
class Users(
        @Column(name = "fullname")
        var fullname: String,
        @Column(name = "position")
        var position: String?,
        @Column(name = "dob")
        var dob: Date,
        @Column(name = "email", unique = true)
        var email: String,
        @Column(name = "password")
        var password: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    @SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", allocationSize = 1)
    @Column(name = "id")
    val id: Int = -1

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    @JsonManagedReference
    var team: Teams?=null

//    @OneToMany(cascade = [(CascadeType.ALL)], mappedBy = "reporter")
//    @JsonBackReference
//    val reporterIssues: List<Issue>? = null
//
//    @OneToMany(cascade = [(CascadeType.ALL)], mappedBy = "solver")
//    @JsonBackReference
//    val solverIssues: List<Issue>? = null
}