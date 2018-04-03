package com.projectx.fisioapp.repository.entitymodel.appointments

import com.google.gson.annotations.Expose

class GetAppointmentsResponse{

    @Expose
    var ok: Boolean? = null
    @Expose
    var result: ResultAppointments? = null
}
