package com.projectx.fisioapp.repository.entitymodel

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
internal class AuthenticateUserEntity(
        val ok: String,
        val token: String
)