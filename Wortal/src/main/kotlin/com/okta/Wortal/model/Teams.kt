package com.okta.Wortal.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import javax.persistence.*

@Entity
@Table(name = "device")
class Teams(
        @Column(name = "type")
        val name: String,
        @Column(name = "brand")
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