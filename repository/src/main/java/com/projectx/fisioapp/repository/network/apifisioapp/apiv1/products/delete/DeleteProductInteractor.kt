package com.projectx.fisioapp.repository.network.apifisioapp.apiv1.products.delete

import com.projectx.fisioapp.repository.entitymodel.catalog.CatalogData

internal interface DeleteProductInteractor {
    fun execute(token: String, id: String, success: (successMessage: String) -> Unit, error: (errorMessage: String) -> Unit)
}