package com.projectx.fisioapp.repository.network.apifisioapp.apiv1.appointments.getAppointments

import com.projectx.fisioapp.repository.entitymodel.appointments.AppoinmentData
import java.util.*

internal interface GetAppointmentsForDateInteractor {
    fun execute(token: String, date: String, success: (appointmentsList: List<AppoinmentData>) -> Unit, error: (errorMessage: String) -> Unit)
}

