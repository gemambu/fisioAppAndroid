package com.projectx.fisioapp.app.models

import java.io.Serializable
import java.util.*

data class Appointment(
        var idService: Int?,
        var idCustomer: String?,
        var idProfessional: Int?,
        var isConfirmed: Boolean?,
        var isCancelled: Boolean?,
        var date: Date,
        var latitude: Float?,
        var longitude: Float?,
        var address: String?,
        var extraInfo: String?): Serializable{

    constructor(customer: String, date: Date): this(null,
            customer,
            null,
            null,
            null,
            date,
            null,
            null,
            null,
            null)

    override fun toString() = date.toString()
}
