package com.projectx.fisioapp.repository.network.apifisioapp.apiv1.products.delete

import com.projectx.fisioapp.repository.entitymodel.catalog.CatalogData

internal interface DeleteProductsInteractor {
    fun execute(token: String, success: (catalogItems: List<CatalogData>) -> Unit, error: (errorMessage: String) -> Unit)
}