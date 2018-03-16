package com.projectx.fisioapp.domain.interactor.users.authenticateuser

import com.projectx.fisioapp.domain.interactor.ErrorCompletion
import com.projectx.fisioapp.domain.interactor.SuccessCompletion


interface AuthenticateUserInteractor {
    fun execute(email: String, password: String, success: SuccessCompletion<String>, error: ErrorCompletion)
}