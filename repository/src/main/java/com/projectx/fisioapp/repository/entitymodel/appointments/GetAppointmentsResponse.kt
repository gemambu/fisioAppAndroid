package com.projectx.fisioapp.repository.entitymodel.appointments

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.projectx.fisioapp.repository.entitymodel.services.ResultServices

class GetAppointmentsResponse{

    @Expose
    var ok: Boolean? = null
    @Expose
    var result: ResultAppointments? = null
}
