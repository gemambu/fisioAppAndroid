package com.projectx.fisioapp.repository.entitymodel.appointments

fun convertAppointments(response: GetAppointmentsResponse): List<Appoinment> {
    var appointmentsList: MutableList<Appoinment> = arrayListOf()

    response.result.let{

        response.result!!.rows.let {

            val rows = response.result!!.rows
            rows.let {

                rows.map {
                    val row = it
                    val appointment = Appoinment(
                            it.id,
                            it.service,
                            it.customer,
                            it.professional,
                            it.isConfirmed,
                            it.isCancelled,
                            it.date,
                            it.latitude,
                            it.longitude,
                            it.extraInfo,
                            it.deleted,
                            it._v
                    )
                    appointmentsList.add(appointment)
                }
            }
        }
    }
    return appointmentsList
}
