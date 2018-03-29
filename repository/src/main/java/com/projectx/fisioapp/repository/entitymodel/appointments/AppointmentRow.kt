package com.projectx.fisioapp.repository.entitymodel.appointments

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.projectx.fisioapp.repository.entitymodel.catalog.Professional
import java.util.*

class AppoinmentRow{
    @SerializedName("_id")
    var id: String? = null
    @Expose
    var service: String? = null
    @Expose
    var customer: String? = null
    @Expose
    var professional: Professional? = null
    @Expose
    var date: Date? = null
    @Expose
    var latitude: Double? = null
    @Expose
    var longitude: Double? = null
    @Expose
    var extraInfo: String? = null
    @SerializedName("__v")
    var v: Int? = null
    @Expose
    var deleted: Boolean? = null
    @Expose
    var isCancelled: Boolean? = null
    @Expose
    var isConfirmed: Boolean? = null

}
