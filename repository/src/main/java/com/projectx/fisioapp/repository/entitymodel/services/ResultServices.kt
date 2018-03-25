package com.projectx.fisioapp.repository.entitymodel.services

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResultServices {

    @SerializedName("rows")
    @Expose
    var rows: List<Row>? = null

}