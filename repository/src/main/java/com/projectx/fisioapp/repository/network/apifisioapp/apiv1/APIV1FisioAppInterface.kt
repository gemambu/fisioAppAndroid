package com.projectx.fisioapp.repository.network.apifisioapp.apiv1

import com.projectx.fisioapp.repository.BuildConfig
import com.projectx.fisioapp.repository.entitymodel.appointments.DeleteAppointmentResponse
import com.projectx.fisioapp.repository.entitymodel.appointments.GetAppointmentsResponse
import com.projectx.fisioapp.repository.entitymodel.responses.AuthenticateUserResponse
import com.projectx.fisioapp.repository.entitymodel.responses.DeleteCatalogResponse
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

    @DELETE(BuildConfig.FISIOAPP_SERVICES_SERVER_PATH + "/{id}")
    fun doDeleteService(@Header("x-access-token") token: String, @Path("id") id: String): Call<DeleteCatalogResponse>

    @GET(BuildConfig.FISIOAPP_PRODUCTS_SERVER_PATH)
    fun doGetProducts(@Header("x-access-token") token: String): Call<GetCatalogResponse>

    @GET(BuildConfig.FISIOAPP_APPOINTMENTS_PROFESSIONAL_SERVER_PATH)
    fun doGetAppointments(@Header("x-access-token") token: String): Call<GetAppointmentsResponse>

    // PENDIENTE SOLUCIONAR CONCATENAR URL Y PARAM DATE
    @GET(BuildConfig.FISIOAPP_APPOINTMENTS_PROFESSIONAL_SERVER_PATH + "?dateFrom={date}&dateTo={date}")
    fun doGetAppointmentsForDate(@Header("x-access-token") token: String, @Path("date") date: String): Call<GetAppointmentsResponse>

    @DELETE(BuildConfig.FISIOAPP_APPOINTMENTS_SERVER_PATH + "/{id}")
    fun doDeleteAppointment(@Header("x-access-token") token: String, @Path("id") id: String): Call<DeleteAppointmentResponse>



}
