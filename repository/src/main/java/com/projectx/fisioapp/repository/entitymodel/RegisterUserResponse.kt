package com.projectx.fisioapp.repository.entitymodel

import com.google.gson.annotations.SerializedName


class RegisterUserResponse {
    @SerializedName("ok") var ok: Boolean? = false
    @SerializedName("result") var result: String? = ""
}
