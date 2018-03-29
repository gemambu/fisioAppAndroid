package com.projectx.fisioapp.repository.network.apifisioapp.apiv1

import com.projectx.fisioapp.repository.BuildConfig
import com.projectx.fisioapp.repository.entitymodel.responses.AuthenticateUserResponse
import com.projectx.fisioapp.repository.entitymodel.responses.RegisterUserResponse
import com.projectx.fisioapp.repository.entitymodel.responses.GetCatalogResponse

import retrofit2.Call
import retrofit2.http.*


internal interface APIV1FisioAppInterface {

    @FormUrlEncoded
    @POST(BuildConfig.FISIOAPP_USERS_AUTHENTICATE_SERVER_PATH)
    fun doGetToken(@Field("email") email: String, @Field("password") password: String): Call<AuthenticateUserResponse>

    @FormUrlEncoded
    @POST(BuildConfig.FISIOAPP_USERS_REGISTER_SERVER_PATH)
    fun doRegisterUser(@Field("name") name: String, @Field("email") email: String, @Field("password") password: String): Call<RegisterUserResponse>

    @GET(BuildConfig.FISIOAPP_SERVICES_SERVER_PATH)
    fun doGetServices(@Header("x-access-token") token: String): Call<GetCatalogResponse>

    @GET(BuildConfig.FISIOAPP_PRODUCTS_SERVER_PATH)
    fun doGetProducts(@Header("x-access-token") token: String): Call<GetCatalogResponse>


}
