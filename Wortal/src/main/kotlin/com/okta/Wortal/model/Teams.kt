package com.okta.Wortal.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import javax.persistence.*

@Entity
@Table(name = "teams")
class Teams(
        @Column(name = "name")
        val name: String,
        @Column(name = "location")
        val location: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teams_id_seq")
    @SequenceGenerator(name = "teams_id_seq", sequenceName = "teams_id_seq", allocationSize = 1)
    @Column(name = "id")
    val id: Int = -1

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    @JsonManagedReference
    var users: Users?=null

//    @ManyToOne
//    @JoinColumn(name = "lab_id")
//    @JsonManagedReference
//    val laboratory: Laboratory?=null

}