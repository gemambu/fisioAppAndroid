package com.projectx.fisioapp.app.models

import java.io.Serializable
import java.util.*

object Appointments: Serializable {

    private var appointments: List<Appointment> = listOf(
            Appointment("Rodrigo",Date(2018-1900, 2, 30, 10, 30)),
            Appointment("Alan", Date(2018-1900, 2, 23)),
            Appointment("Gema",Date(2018-1900, 2, 25)),
            Appointment("Carlos",Date(2018-1900, 2, 25))
    )

    operator fun get(i: Int) = appointments[i]

    fun toArray() = appointments.toTypedArray()
}
