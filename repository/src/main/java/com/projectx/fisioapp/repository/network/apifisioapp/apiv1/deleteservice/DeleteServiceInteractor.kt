package com.projectx.fisioapp.repository.network.apifisioapp.apiv1.getservice

import com.projectx.fisioapp.repository.entitymodel.catalog.CatalogData

internal interface DeleteServiceInteractor {
    fun execute(token: String, id: String, success: (catalogItem: CatalogData) -> Unit, error: (errorMessage: String) -> Unit)
}