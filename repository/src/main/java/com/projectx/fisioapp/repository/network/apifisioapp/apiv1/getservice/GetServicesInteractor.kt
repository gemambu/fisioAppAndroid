package com.projectx.fisioapp.repository.network.apifisioapp.apiv1.getservice

import com.projectx.fisioapp.repository.model.CatalogData

internal interface GetServicesInteractor {
    fun execute(token: String, success: (catalogItems: List<CatalogData>) -> Unit, error: (errorMessage: String) -> Unit)
}