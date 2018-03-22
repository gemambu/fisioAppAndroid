package com.projectx.fisioapp.repository.network.apifisioapp.apiv1.registeruser

internal interface RegisterUserInteractor {
    fun execute(name: String, email: String, password: String, success: (ok: Boolean) -> Unit, error: (errorMessage: String) -> Unit)
}