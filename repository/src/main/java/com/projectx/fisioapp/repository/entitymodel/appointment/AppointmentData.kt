package com.projectx.fisioapp.repository.entitymodel.appointment

import com.google.gson.annotations.SerializedName
import java.util.*

data class AppointmentData(
        @SerializedName("_id") val databaseId: String,
        @SerializedName("serviceId") val serviceId: String,
        @SerializedName("customer")val customerId: String,
        @SerializedName("professional")val professionalId: String,
        @SerializedName("isConfirmed")val isConfirmed: Boolean,
        @SerializedName("isCancelled")val isCancelled: Boolean,
        @SerializedName("date") val date: Date,
        @SerializedName("latitude") val latitude: String,
        @SerializedName("longitude")  val longitude: String,
        @SerializedName("extraInfo") val extraInfo: String
)