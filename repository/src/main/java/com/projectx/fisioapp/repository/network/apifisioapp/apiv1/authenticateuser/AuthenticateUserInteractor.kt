package com.projectx.fisioapp.repository.network.apifisioapp.apiv1.authenticateuser


internal interface AuthenticateUserInteractor {
    fun execute(email: String, password: String, success: (token: String) -> Unit, error: (errorMessage: String) -> Unit)
}