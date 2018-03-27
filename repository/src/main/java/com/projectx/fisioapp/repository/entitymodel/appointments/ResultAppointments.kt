package com.projectx.fisioapp.repository.entitymodel.appointments

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResultAppointments {

    @SerializedName("rows")
    @Expose
    var rows: List<Appoinment>? = null

}
