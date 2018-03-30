package com.projectx.fisioapp.repository.entitymodel.appointments

import com.google.gson.annotations.Expose

class DeleteAppointmentResponse {

    @Expose
    var ok: Boolean? = null
    @Expose
    var message: String? = null
}
