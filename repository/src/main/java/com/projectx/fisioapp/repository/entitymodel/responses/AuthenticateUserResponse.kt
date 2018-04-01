package com.projectx.fisioapp.repository.entitymodel.responses

import com.google.gson.annotations.SerializedName


class AuthenticateUserResponse (
    @field:SerializedName("ok") var ok: Boolean,
    @field:SerializedName("token") var token: String
)
