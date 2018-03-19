package com.projectx.fisioapp.repository.entitymodel

import com.fasterxml.jackson.annotation.JsonIgnoreProperties


@JsonIgnoreProperties(ignoreUnknown = true)
internal class AuthenticateUserResponseEntity(
        val result: AuthenticateUserEntity
)