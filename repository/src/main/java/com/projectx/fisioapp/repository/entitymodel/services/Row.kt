package com.projectx.fisioapp.repository.entitymodel.services

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Row {

    @SerializedName("_id")
    @Expose
    var id: String? = null
    @SerializedName("professional")
    @Expose
    var professional: Professional? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("price")
    @Expose
    var price: Int? = null
    @SerializedName("__v")
    @Expose
    var v: Int? = null
    @SerializedName("deleted")
    @Expose
    var deleted: Boolean? = null
    @SerializedName("isActive")
    @Expose
    var isActive: Boolean? = null
    @SerializedName("image")
    @Expose
    var image: String? = null

}