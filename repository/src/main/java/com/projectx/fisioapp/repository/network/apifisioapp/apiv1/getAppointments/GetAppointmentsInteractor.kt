package com.projectx.fisioapp.repository.network.apifisioapp.apiv1.getAppointments

import com.projectx.fisioapp.repository.entitymodel.appointments.Appoinment

internal interface GetAppointmentsInteractor {
    fun execute(token: String, success: (appointmentsList: List<Appoinment>) -> Unit, error: (errorMessage: String) -> Unit)
}