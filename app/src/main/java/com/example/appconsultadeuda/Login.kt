package com.example.appconsultadeuda

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.appconsultadeuda.DATAGLOBAL.Companion.prefs
import com.example.appconsultadeuda.ui.actyRescuePassword
import com.example.appconsultadeuda.ui.actyTabLayout
import com.example.appconsultadeuda.databinding.ActivityLoginBinding
import com.example.appconsultadeuda.entity.DataLoginCuota.LoginComercialResponse
import com.example.appconsultadeuda.entity.DataLoginCuota.dataLogin
import com.example.appconsultadeuda.ui.actyFirstLogin
import com.unosoft.ecomercialapp.api.APIClient.client
import com.unosoft.ecomercialapp.api.LoginApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Login : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding

    var apiInterface: LoginApi? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        login()
        eventsHandlers()
    }

    private fun eventsHandlers() {
        binding.tvRescuePassword.setOnClickListener {
            val i = Intent(this, actyRescuePassword::class.java)
            startActivity(i)
        }
    }

    fun login(){
        val user = binding.user
        val pass = binding.pass
        val ingresar = binding.btnIngresar

        if (prefs.getUser().isNotEmpty()){
            user.setText(prefs.getUser())
        }

        val pd = ProgressDialog(this)
        pd.setMessage("Validando usuario....")
        pd.setCancelable(false)
        pd.create()

        ingresar.setOnClickListener(View.OnClickListener {
            if (user.text.toString().length == 0 || pass.text.toString().length == 0) {
                AlertMessage("Datos inválidos")
                return@OnClickListener
            } else {
                prefs.save_User(user.text.toString())

                println(prefs.getURLBase())
                println(prefs.getUser())

                pd.show()

                //*******  MANTENER
                val _user = dataLogin(user.text.toString(), pass.text.toString())
                apiInterface = client!!.create(LoginApi::class.java)
                val call1 = apiInterface!!.postLogin(_user)
                call1.enqueue(object : Callback<LoginComercialResponse> {
                    override fun onResponse(call: Call<LoginComercialResponse>, response: Response<LoginComercialResponse>) {
                        if (response.code() == 400) {
                            AlertMessage("Usuario y/o Contraseña incorrecta")
                            pd.cancel()
                        }else {
                            val user1 = response.body()!!
                            prefs.save_User(user.text.toString())
                            prefs.save_Name(user1.nombre)

                            if (user1.flogin == 1){
                                val i = Intent(applicationContext, actyFirstLogin::class.java)
                                startActivity(i)
                                pass.setText("")
                                pd.cancel()
                            }else{
                                val i = Intent(applicationContext, VistaGeneral::class.java)
                                startActivity(i)
                                pass.setText("")
                                pd.cancel()
                            }

                        }
                    }

                    override fun onFailure(call: Call<LoginComercialResponse>, t: Throwable) {
                        pd.cancel()
                        AlertMessage("Error de Conexión: " + t.message)
                        call.cancel()
                    }
                })
                //*******  MANTENER ********
            }
        })
    }

    fun AlertMessage(mensaje: String?) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Información")
        builder.setMessage(mensaje)
        builder.setCancelable(false)
        builder.setPositiveButton("Ok") { dialog, which -> dialog.dismiss() }
        val dialogMessage = builder.create()
        dialogMessage.show()
    }

}