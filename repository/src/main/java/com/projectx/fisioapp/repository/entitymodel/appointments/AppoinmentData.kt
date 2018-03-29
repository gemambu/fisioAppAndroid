package com.projectx.fisioapp.repository.entitymodel.appointments

import com.google.gson.annotations.SerializedName
import java.util.*

data class AppoinmentData(
        @SerializedName("_id") val databaseId: String,
        @SerializedName("serviceId") val serviceId: String,
        @SerializedName("servicePrice") val servicePrice: String,
        @SerializedName("customer")val customerId: String,
        @SerializedName("customerName")val customerName: String,
        @SerializedName("professional")val professionalId: String,
        @SerializedName("isConfirmed")val isConfirmed: Boolean,
        @SerializedName("isCancelled")val isCancelled: Boolean,
        @SerializedName("date") val date: Date,
        @SerializedName("latitude") val latitude: String,
        @SerializedName("longitude")  val longitude: String,
        @SerializedName("extraInfo") val extraInfo: String
)