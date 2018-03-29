package com.projectx.fisioapp.repository.entitymodel.appointments

import com.google.gson.annotations.SerializedName
import com.projectx.fisioapp.repository.entitymodel.catalog.Professional
import java.util.*

data class Appoinment(
    @SerializedName("_id") var id: String,
    @SerializedName("service") var service: String,
    @SerializedName("customer") var customer: String,
    @SerializedName("professional") var professional: Professional,
    @SerializedName("isConfirmed") var isConfirmed: Boolean,
    @SerializedName("isCancelled") var isCancelled: Boolean,
    @SerializedName("date") var date: Date,
    @SerializedName("latitude") var latitude: Double,
    @SerializedName("longitude") var longitude: Double,
    @SerializedName("extraInfo") var extraInfo: String,
    @SerializedName("deleted") var deleted: Boolean,
    @SerializedName("_v") var _v: Int
)