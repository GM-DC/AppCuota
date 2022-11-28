package com.example.appconsultadeuda.api

import com.example.appconsultadeuda.entity.DataRecoveryPassword.dcConfirmCode
import com.example.appconsultadeuda.entity.DataRecoveryPassword.dcRecoveryPassword
import com.example.appconsultadeuda.entity.DataRecoveryPassword.dcResendCode
import com.example.appconsultadeuda.entity.DataRecoveryPassword.dcUpdatePassword
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RecoveryPasswordApi {
    @POST("api/Users/LoginClienteCuota/RecoveryPassword")
    fun postEnterDoc(@Body enterDoc: dcRecoveryPassword): Call<Void>

    @POST("api/Users/LoginClienteCuota/ConfirmCode")
    fun postEnterCode(@Body enterCode: dcConfirmCode): Call<Void>

    @POST("api/Users/LoginClienteCuota/UpdatePassword")
    fun postUpdatePassword(@Body updateDate: dcUpdatePassword): Call<Void>

    @POST("api/Users/LoginClienteCuota/ResendCode")
    fun postResendCode(@Body enterDoc: dcResendCode): Call<Void>
}