package com.projectx.fisioapp.repository.network.apifisioapp.apiv1

import com.projectx.fisioapp.repository.BuildConfig
import com.projectx.fisioapp.repository.entitymodel.AuthenticateUserResponse
import com.projectx.fisioapp.repository.entitymodel.RegisterUserResponse

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


internal interface APIV1FisioAppInterface {

    @FormUrlEncoded
    @POST(BuildConfig.FISIOAPP_USERS_AUTHENTICATE_SERVER_PATH)
    fun doGetToken(@Field("email") email: String, @Field("password") password: String): Call<AuthenticateUserResponse>

    @FormUrlEncoded
    @POST(BuildConfig.FISIOAPP_USERS_REGISTER_SERVER_PATH)
    fun doRegisterUser(@Field("name") name: String, @Field("email") email: String, @Field("password") password: String): Call<RegisterUserResponse>

}
