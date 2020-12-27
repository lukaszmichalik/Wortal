package com.okta.Wortal.dto

import com.fasterxml.jackson.annotation.JsonCreator
import java.sql.Date

data class UserData @JsonCreator constructor(
        val id: Int?,
        val fullname: String,
        //val position: String,
        val dob: Date,
        val email: String,
        var password:String
)