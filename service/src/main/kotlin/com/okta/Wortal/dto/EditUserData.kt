package com.okta.Wortal.dto

import com.fasterxml.jackson.annotation.JsonCreator

data class EditUserData @JsonCreator constructor(
        val id: Int,
        val fullname: String,
        val position: String,
        val age: String,
        val email: String,
        var password:String
)