package com.projectx.fisioapp.repository.network

import com.projectx.fisioapp.repository.ErrorCompletion
import com.projectx.fisioapp.repository.SuccessCompletion
import org.json.JSONObject

internal interface GetJsonManager {
    fun execute(url: String,
                success: SuccessCompletion<String>,
                error: ErrorCompletion)
    fun executePost(url: String,
                    success: SuccessCompletion<String>,
                    error: ErrorCompletion)
}
