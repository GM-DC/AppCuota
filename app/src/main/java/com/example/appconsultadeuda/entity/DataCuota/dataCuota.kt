package com.example.appconsultadeuda.entity.DataCuota

data class dataCuota(
    val año: Int,
    val cuotas: List<Cuota>
)

data class Cuota(
    val mes: String,
    val fechaVencimiento: String,
    val importe: String,
    val estado: String,
    val numeroSerie: String,
    val cuota: Int
)