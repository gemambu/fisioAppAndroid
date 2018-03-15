package com.projectx.fisioapp.repository.internetstatus

import com.projectx.fisioapp.repository.CodeClosure
import com.projectx.fisioapp.repository.ErrorClosure

interface  InternetStatusInteractor {
    fun execute(success: CodeClosure, error: ErrorClosure)
}
