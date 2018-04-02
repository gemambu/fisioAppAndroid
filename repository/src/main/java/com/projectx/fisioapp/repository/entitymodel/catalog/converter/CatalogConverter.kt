package com.projectx.fisioapp.repository.entitymodel.catalog.converter

import com.projectx.fisioapp.repository.entitymodel.responses.GetCatalogResponse
import com.projectx.fisioapp.repository.entitymodel.catalog.CatalogData
import com.projectx.fisioapp.repository.entitymodel.catalog.CatalogType


fun convertList(response: GetCatalogResponse, type: CatalogType): List<CatalogData> {
    var catalogDataList: MutableList<CatalogData> = arrayListOf()


    response.result.let {

        response.result!!.rows.let {

            val rows = response.result!!.rows
            rows.let {

                rows!!.map {
                    val row = it
                    val catalog = CatalogData(
                            row.id!!,
                            row.name!!,
                            row.description!!,
                            row.price!!.toFloat(),
                            // CHANGING PROFESSIONAL X PERSON
                            row.professional!!.id!!,
                            row.isActive!!,
                            //row?.image!!,
                            type
                    )
                    catalogDataList.add(catalog)
                }
            }

        }

    }



    return catalogDataList
}