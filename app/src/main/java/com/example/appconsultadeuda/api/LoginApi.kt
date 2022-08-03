package com.unosoft.ecomercialapp.api
import com.example.appconsultadeuda.entity.DataLoginCuota.LoginComercialResponse
import com.example.appconsultadeuda.entity.DataLoginCuota.dataLogin
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body

interface LoginApi {
    @POST("api/Users/LoginClienteCuota")
    fun postLogin(@Body loginMozo: dataLogin): Call<LoginComercialResponse>

    @POST("api/Users/LoginComercial")
    suspend fun checkLoginComanda(@Body loginMozo: dataLogin) : Response<LoginComercialResponse>
}