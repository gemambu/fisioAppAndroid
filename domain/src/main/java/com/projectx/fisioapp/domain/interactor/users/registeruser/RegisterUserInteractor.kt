package com.projectx.fisioapp.domain.interactor.users.registeruser

import com.projectx.fisioapp.domain.interactor.ErrorCompletion
import com.projectx.fisioapp.domain.interactor.SuccessCompletion


interface RegisterUserInteractor {
    fun execute(name: String, email: String, password: String, success: SuccessCompletion<Boolean>, error: ErrorCompletion)
}