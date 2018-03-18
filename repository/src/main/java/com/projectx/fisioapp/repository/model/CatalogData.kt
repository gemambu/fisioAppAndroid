package com.projectx.fisioapp.repository.model

import java.io.Serializable

data class CatalogData(
        val databaseId: Long,
        val name: String,
        val description: String,
        val price: Float,
        val professionalId: Long,
        val isActive: Boolean,
        val type: String = ""
)

enum class CatalogType(val type: Int) : Serializable {
    SERVICE(1),
    PRODUCT(2)
}