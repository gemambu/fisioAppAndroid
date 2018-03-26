package com.projectx.fisioapp.repository.entitymodel.services

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GetServicesResponse {

    @SerializedName("ok")
    @Expose
    var ok: Boolean? = null
    @SerializedName("result")
    @Expose
    var result: ResultServices? = null

}