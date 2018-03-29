package com.projectx.fisioapp.repository.entitymodel.appointments

import com.google.gson.annotations.SerializedName
import com.projectx.fisioapp.repository.entitymodel.catalog.Professional
import java.util.*

data class Appoinment(
    @SerializedName("_id") var id: String? = null,
    @SerializedName("service") var service: String? = null,
    @SerializedName("customer") var customer: String? = null,
    @SerializedName("professional") var professional: Professional? = null,
    @SerializedName("isConfirmed") var isConfirmed: Boolean? = null,
    @SerializedName("isCancelled") var isCancelled: Boolean? = null,
    @SerializedName("date") var date: Date? = null,
    @SerializedName("latitude") var latitude: Double? = null,
    @SerializedName("longitude") var longitude: Double? = null,
    @SerializedName("extraInfo") var extraInfo: String? = null,
    @SerializedName("deleted") var deleted: Boolean? = null,
    @SerializedName("_v") var _v: Int? = null
)