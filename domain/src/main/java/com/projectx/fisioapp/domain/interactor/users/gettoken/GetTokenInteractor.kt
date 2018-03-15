package com.projectx.fisioapp.domain.interactor.users.gettoken

import com.projectx.fisioapp.domain.interactor.ErrorCompletion
import com.projectx.fisioapp.domain.interactor.SuccessCompletion


interface GetTokenInteractor {
    fun execute(success: SuccessCompletion<String>, error: ErrorCompletion)
}