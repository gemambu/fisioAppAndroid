package com.projectx.fisioapp.repository.entitymodel.appointments

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.projectx.fisioapp.repository.entitymodel.services.Professional
import java.util.*

class Appoinment{

    @SerializedName("_id")
    @Expose
    var id: String? = null
    @SerializedName("service")
    @Expose
    var service: String? = null
    @SerializedName("customer")
    @Expose
    var customer: String? = null
    @SerializedName("professional")
    @Expose
    var professional: Professional? = null
    @SerializedName("isConfirmed")
    @Expose
    var isConfirmed: Boolean? = null
    @SerializedName("isCancelled")
    @Expose
    var isCancelled: Boolean? = null
    @SerializedName("date")
    @Expose
    var date: Date? = null
    @SerializedName("latitude")
    @Expose
    var latitude: Double? = null
    @SerializedName("longitude")
    @Expose
    var longitude: Double? = null
    @SerializedName("extraInfo")
    @Expose
    var extraInfo: String? = null
    @SerializedName("deleted")
    @Expose
    var deleted: Boolean? = null
    @SerializedName("_v")
    @Expose
    var _v: Int? = null
}