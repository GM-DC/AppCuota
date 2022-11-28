package com.example.appconsultadeuda.helpers

class utils {
    fun pricetostringformat(valuenumeric: Double): String {
        return String.format("%,.2f", valuenumeric)
    }
}