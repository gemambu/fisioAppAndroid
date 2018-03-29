package com.projectx.fisioapp.repository.entitymodel.appointments

fun convertAppointments(response: GetAppointmentsResponse): List<Appoinment> {
    var appointmentsList: MutableList<Appoinment> = arrayListOf()

    response.result.let{

        response.result!!.rows.let {

            val rows = response.result!!.rows
            rows.let {

                rows!!.map {
                    val name = it.customer!!.name + " " + it.customer!!.lastName
                    val appointment = Appoinment(
                            it.id!!,
                            it.service!!.databaseId,
                            it.service!!.price.toString(),
                            it.customer!!.id!!,
                            name,
                            it.professional!!.id!!,
                            it.isConfirmed!!,
                            it.isCancelled!!,
                            it.date!!,
                            it.latitude.toString()!!,
                            it.longitude.toString()!!,
                            it.extraInfo!!
                    )
                    appointmentsList.add(appointment)
                }
            }
        }
    }
    return appointmentsList
}
