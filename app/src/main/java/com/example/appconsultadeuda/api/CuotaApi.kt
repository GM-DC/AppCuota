package com.example.appconsultadeuda.api

import com.example.appconsultadeuda.DATAGLOBAL.Companion.prefs
import com.example.appconsultadeuda.entity.DataCuota.dataCuota
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CuotaApi {
    @GET("api/CuotasComercial/GetAllCuotasComercial")
    suspend fun getRuc(@Query("Ruc") Ruc:String) : Response<List<dataCuota>>

    @GET("api/CuotasComercial/GetAllCuotasComercial")
    suspend fun getAnoRuc(@Query("Ruc") Ruc:String, @Query("Año") Ano:String) : Response<List<dataCuota>>

}