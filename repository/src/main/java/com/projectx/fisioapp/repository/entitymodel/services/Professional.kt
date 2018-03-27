package com.projectx.fisioapp.repository.entitymodel.services

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Professional {

    @SerializedName("_id")
    @Expose
    var id: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("password")
    @Expose
    var password: String? = null // DE VERDAD HACE FALTA TRAERSE EL PASSWORD DEL PROFESIONAL?
    @SerializedName("email")
    @Expose
    var email: String? = null
    @SerializedName("isProfessional")
    @Expose
    var isProfessional: Boolean? = null
    @SerializedName("gender")
    @Expose
    var gender: String? = null
    @SerializedName("lastName")
    @Expose
    var lastName: String? = null
    @SerializedName("address")
    @Expose
    var address: String? = null
    @SerializedName("__v")
    @Expose
    var v: Int? = null
    @SerializedName("deleted")
    @Expose
    var deleted: Boolean? = null

}