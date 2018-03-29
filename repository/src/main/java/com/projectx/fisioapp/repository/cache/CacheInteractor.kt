package com.projectx.fisioapp.repository.cache

import com.projectx.fisioapp.repository.entitymodel.catalog.CatalogData


interface CacheInteractor {

    /******** users ********/


    /******** catalog (products and services) ********/
    fun getAllCatalogItems(type: String, success: (catalogList: List<CatalogData>) -> Unit, error: (errorMessage: String) -> Unit)
    fun deleteAllCatalogItems(success: () -> Unit, error: (errorMessage: String) -> Unit)
    fun countCatalogItems(): Int
    fun saveAllCatalogItems(type: String, catalogList: List<CatalogData>, success: () -> Unit, error: (errorMessage: String) -> Unit)
    fun deleteService(id: String, success: () -> Unit, error: (errorMessage: String) -> Unit)

}