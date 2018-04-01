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
<<<<<<< HEAD
                                row?.id!!,
                                row?.name!!,
                                row?.description!!,
                                row?.price!!.toFloat()!!,
                                row?.person!!.id!!,
                                row?.isActive!!,
                                row?.image!!,
                                CatalogType.SERVICE
=======
                                row.id!!,
                                row.name!!,
                                row.description!!,
                                row.price!!.toFloat(),
                                row.professional!!.id!!,
                                row.isActive!!,
                                //row?.image!!,
                                type
>>>>>>> services_and_products_gema
                        )
                        catalogDataList.add(catalog)
                    }
                }

            }

        }



        return catalogDataList
    }
