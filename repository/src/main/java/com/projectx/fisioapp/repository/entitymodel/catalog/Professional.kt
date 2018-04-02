package com.projectx.fisioapp.repository.entitymodel.catalog

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Professional {

    @SerializedName("_id")
    var id: String? = null
    @Expose
    var name: String? = null
    @Expose
    var isProfessional: Boolean? = null
    @Expose
    var gender: String? = null
    @Expose
    var lastName: String? = null
    @Expose
    var address: String? = null
    @SerializedName("__v")
    var v: Int? = null
    @Expose
    var deleted: Boolean? = null

}