package com.projectx.fisioapp.repository.network

import com.projectx.fisioapp.repository.ErrorCompletion
import com.projectx.fisioapp.repository.SuccessCompletion

internal interface GetJsonManager {
    fun execute(url: String,
                success: SuccessCompletion<String>,
                error: ErrorCompletion)
}
