package com.projectx.fisioapp.repository.network.apifisioapp.apiv1

import com.projectx.fisioapp.repository.BuildConfig
<<<<<<< HEAD
import com.projectx.fisioapp.repository.entitymodel.appointments.DeleteAppointmentResponse
import com.projectx.fisioapp.repository.entitymodel.appointments.GetAppointmentsResponse
import com.projectx.fisioapp.repository.entitymodel.responses.AuthenticateUserResponse
import com.projectx.fisioapp.repository.entitymodel.responses.DeleteCatalogResponse
import com.projectx.fisioapp.repository.entitymodel.responses.RegisterUserResponse
import com.projectx.fisioapp.repository.entitymodel.responses.GetCatalogResponse
=======
import com.projectx.fisioapp.repository.entitymodel.responses.*

>>>>>>> services_and_products_gema
import retrofit2.Call
import retrofit2.http.*


internal interface APIV1FisioAppInterface {

    /******** USERS OPERATIONS *********/

    @FormUrlEncoded
    @POST(BuildConfig.FISIOAPP_USERS_AUTHENTICATE_SERVER_PATH)
    fun doGetToken(@Field("email") email: String, @Field("password") password: String): Call<AuthenticateUserResponse>

    @FormUrlEncoded
    @POST(BuildConfig.FISIOAPP_USERS_REGISTER_SERVER_PATH)
    fun doRegisterUser(@Field("name") name: String, @Field("email") email: String, @Field("password") password: String): Call<RegisterUserResponse>


    /******** SERVICES OPERATIONS *********/

    @GET(BuildConfig.FISIOAPP_SERVICES_SERVER_PATH)
    fun doGetServices(@Header("x-access-token") token: String): Call<GetCatalogResponse>

    @DELETE(BuildConfig.FISIOAPP_SERVICES_SERVER_PATH + "/{id}")
    fun doDeleteService(@Header("x-access-token") token: String, @Path("id") id: String): Call<DeleteCatalogResponse>

<<<<<<< HEAD
    @GET(BuildConfig.FISIOAPP_PRODUCTS_SERVER_PATH)
    fun doGetProducts(@Header("x-access-token") token: String): Call<GetCatalogResponse>

    @GET(BuildConfig.FISIOAPP_APPOINTMENTS_PROFESSIONAL_SERVER_PATH)
    fun doGetAppointments(@Header("x-access-token") token: String): Call<GetAppointmentsResponse>

    @GET(BuildConfig.FISIOAPP_APPOINTMENTS_PROFESSIONAL_SERVER_PATH/* + "?dateFrom={date}&dateTo={date}"*/)
    fun doGetAppointmentsForDate(@Header("x-access-token") token: String, @Query("dateFrom") dateFrom: String, @Query("dateTo") dateTo: String): Call<GetAppointmentsResponse>


    @DELETE(BuildConfig.FISIOAPP_APPOINTMENTS_SERVER_PATH + "/{id}")
    fun doDeleteAppointment(@Header("x-access-token") token: String, @Path("id") id: String): Call<DeleteAppointmentResponse>

=======
    @FormUrlEncoded
    @POST(BuildConfig.FISIOAPP_SERVICES_SERVER_PATH)
    fun doInsertService(@Header("x-access-token") token: String,
                        @Field("name") name: String,
                        @Field("description") description: String,
                        @Field("price") price: Float,
                        @Field("isActive") isActive: Boolean): Call<SaveCatalogResponse>

    @FormUrlEncoded
    @PUT(BuildConfig.FISIOAPP_SERVICES_SERVER_PATH + "/{id}")
    fun doUpdateService(@Header("x-access-token") token: String,
                        @Path("id") id: String,
                        @Field("name") name: String,
                        @Field("description") description: String,
                        @Field("price") price: Float,
                        @Field("isActive") isActive: Boolean): Call<SaveCatalogResponse>


    /******** PRODUCTS OPERATIONS *********/
    @GET(BuildConfig.FISIOAPP_PRODUCTS_SERVER_PATH)
    fun doGetProducts(@Header("x-access-token") token: String): Call<GetCatalogResponse>

    @DELETE(BuildConfig.FISIOAPP_PRODUCTS_SERVER_PATH + "/{id}")
    fun doDeleteProduct(@Header("x-access-token") token: String, @Path("id") id: String): Call<DeleteCatalogResponse>
>>>>>>> services_and_products_gema

    @FormUrlEncoded
    @POST(BuildConfig.FISIOAPP_PRODUCTS_SERVER_PATH)
    fun doInsertProduct(@Header("x-access-token") token: String,
                        @Field("name") name: String,
                        @Field("description") description: String,
                        @Field("price") price: Float,
                        @Field("isActive") isActive: Boolean): Call<SaveCatalogResponse>

    @FormUrlEncoded
    @PUT(BuildConfig.FISIOAPP_PRODUCTS_SERVER_PATH + "/{id}")
    fun doUpdateProduct(@Header("x-access-token") token: String,
                        @Path("id") id: String,
                        @Field("name") name: String,
                        @Field("description") description: String,
                        @Field("price") price: Float,
                        @Field("isActive") isActive: Boolean): Call<SaveCatalogResponse>
}
