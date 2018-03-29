package com.projectx.fisioapp.repository.entitymodel.services

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GetServicesResponse {

    @Expose
    var ok: Boolean? = null
    @Expose
    var result: ResultServices? = null

}