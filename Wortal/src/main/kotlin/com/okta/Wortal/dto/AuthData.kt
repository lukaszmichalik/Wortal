package com.okta.Wortal.dto

import com.fasterxml.jackson.annotation.JsonCreator

data class AuthData @JsonCreator constructor(
        var email: String,
        var password: String
)