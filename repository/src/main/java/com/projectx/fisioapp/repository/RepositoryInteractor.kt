package com.projectx.fisioapp.repository

interface RepositoryInteractor {

    // Users
    fun authenticateUser(email: String, password: String,
                         success: (token: String) -> Unit, error: (errorMessage: String) -> Unit)
    fun getToken(success: (token: String) -> Unit, error: (errorMessage: String) -> Unit)

}
