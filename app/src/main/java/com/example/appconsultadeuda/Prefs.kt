package com.example.apppedido

import android.content.Context
import android.os.Build
import android.preference.PreferenceManager
import androidx.annotation.RequiresApi
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class Prefs (val contexto: Context){

    val SHARE_DB = "Mydtb"

    val SHARE_URLBASE = "URLBASE"
    val SHARE_USER = "USER"
    val SHARE_NAME = "NAME"


    val storege = contexto.getSharedPreferences(SHARE_DB,0)

    //*********    PRUEBA  ************

    //******************  GUARDAR DATOS  ****************************
    fun save_URLBase(URLBASE:String){
        storege.edit().putString(SHARE_URLBASE,URLBASE).apply()
    }
    fun save_User(USER:String){
        storege.edit().putString(SHARE_USER,USER).apply()
    }
    fun save_Name(NAME:String){
        storege.edit().putString(SHARE_NAME,NAME).apply()
    }

    //********************* CONSULTAR DATOS ************************
    fun getURLBase(): String {
        return storege.getString(SHARE_URLBASE,"")!!
    }
    fun getUser(): String {
        return storege.getString(SHARE_USER,"")!!
    }
    fun getName(): String {
        return storege.getString(SHARE_NAME,"")!!
    }

    fun wipe(){
        storege.edit().clear().apply()
    }
}