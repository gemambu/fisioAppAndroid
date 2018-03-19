package com.projectx.fisioapp.app.models

import java.io.Serializable
import java.util.*

object Appointments: Serializable {

    private var appointments: List<Appointment> = listOf(
            Appointment(Date(2018, 2, 22)),
            Appointment(Date("23/03/2018")),
            Appointment(Date(2018, 2, 23)),
            Appointment(Date(2018, 2, 25))
    )

    operator fun get(i: Int) = appointments[i]

    fun toArray() = appointments.toTypedArray()
}
