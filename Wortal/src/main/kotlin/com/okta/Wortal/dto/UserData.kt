package com.okta.Wortal.dto

import com.fasterxml.jackson.annotation.JsonCreator

data class UserData @JsonCreator constructor(
        val id: Int?,
        val name: String,
        val lastname: String,
        val team: String,
        val age: String,
        val login: String,
        var password:String
)