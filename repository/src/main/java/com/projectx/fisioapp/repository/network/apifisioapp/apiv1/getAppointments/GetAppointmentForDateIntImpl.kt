package com.projectx.fisioapp.repository.network.apifisioapp.apiv1.getAppointments

import com.projectx.fisioapp.repository.entitymodel.appointments.AppoinmentData

class GetAppointmentForDateIntImpl : GetAppointmentForDateInteractor {
    override fun execute(token: String, date: String, success: (appointmentsList: List<AppoinmentData>) -> Unit, error: (errorMessage: String) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}