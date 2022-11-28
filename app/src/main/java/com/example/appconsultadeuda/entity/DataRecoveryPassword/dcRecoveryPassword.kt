package com.example.appconsultadeuda.entity.DataRecoveryPassword

data class dcRecoveryPassword(
    val docIdentidad: String
)

data class dcConfirmCode(
    val code: String,
    val docIdentidad: String
)

data class dcUpdatePassword(
    val newPassword: String,
    val docIdentidad: String
)

data class dcResendCode(
    val docIdentidad: String
)
