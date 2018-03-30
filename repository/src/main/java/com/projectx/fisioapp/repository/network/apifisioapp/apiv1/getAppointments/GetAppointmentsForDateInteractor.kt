package com.projectx.fisioapp.repository.network.apifisioapp.apiv1.getAppointments

import com.projectx.fisioapp.repository.entitymodel.appointments.AppoinmentData

internal interface GetAppointmentsForDateInteractor {
    fun execute(token: String, dateFrom: String, dateTo: String, success: (appointmentsList: List<AppoinmentData>) -> Unit, error: (errorMessage: String) -> Unit)
}

