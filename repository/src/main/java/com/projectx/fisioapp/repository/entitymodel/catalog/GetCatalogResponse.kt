package com.projectx.fisioapp.repository.entitymodel.catalog

import com.google.gson.annotations.Expose

class GetCatalogResponse {

    @Expose
    var ok: Boolean? = null
    @Expose
    var result: ResultCatalogItems? = null

}