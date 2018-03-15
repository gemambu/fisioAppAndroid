package com.projectx.fisioapp.repository.cache


interface CacheInteractor {

    // Users
    fun getToken(success: (token: String) -> Unit,
                error: (errorMessage: String) -> Unit)

}