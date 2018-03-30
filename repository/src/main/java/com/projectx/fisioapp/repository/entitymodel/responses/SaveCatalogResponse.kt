package com.projectx.fisioapp.repository.entitymodel.responses

import com.google.gson.annotations.Expose

class SaveCatalogResponse {

    @Expose
    var ok: Boolean? = null
    @Expose
    var result: String? = null

}