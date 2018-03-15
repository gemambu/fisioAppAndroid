package com.projectx.fisioapp.repository.internetstatus

import com.projectx.fisioapp.repository.CodeClosure
import com.projectx.fisioapp.repository.ErrorClosure

class InternetStatusIntFakeImpl: InternetStatusInteractor {
    override fun execute(success: CodeClosure, error: ErrorClosure) {
        success()
    }
}