package com.projectx.fisioapp.repository.entitymodel.entitymodelexample

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class ServiceList {

    @SerializedName("page")
    var page: Int? = null
    @SerializedName("per_page")
    var perPage: Int? = null
    @SerializedName("total")
    var total: Int? = null
    @SerializedName("total_pages")
    var totalPages: Int? = null
    @SerializedName("data")
    var data: List<Datum> = ArrayList()

    inner class Datum {
        @SerializedName("id")
        var id: Int? = null
        @SerializedName("name")
        var name: String? = null
        @SerializedName("description")
        var description: String? = null
        @SerializedName("price")
        var price: Float? = null
        @SerializedName("isActive")
        var isActive: Boolean? = null
        @SerializedName("image")
        var image: String? = null
    }

}