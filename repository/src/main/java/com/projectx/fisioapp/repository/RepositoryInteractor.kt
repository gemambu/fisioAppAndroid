package com.projectx.fisioapp.repository

interface RepositoryInteractor {

    // Users
    fun authenticateUser(email: String, password: String,
                         success: (token: String) -> Unit, error: (errorMessage: String) -> Unit)
    fun registerUser(name: String, email: String, password: String,
                         success: (ok: Boolean) -> Unit, error: (errorMessage: String) -> Unit)

}
