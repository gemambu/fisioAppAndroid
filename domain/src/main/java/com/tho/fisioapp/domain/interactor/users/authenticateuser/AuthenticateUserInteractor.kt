package com.tho.fisioapp.domain.interactor.users.authenticateuser

import com.tho.fisioapp.domain.interactor.ErrorCompletion
import com.tho.fisioapp.domain.interactor.SuccessCompletion


interface AuthenticateUserInteractor {
    fun execute(email: String, password: String, success: SuccessCompletion<String>, error: ErrorCompletion)
}