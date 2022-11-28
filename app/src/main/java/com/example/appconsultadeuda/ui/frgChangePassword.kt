package com.example.appconsultadeuda.ui

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.appconsultadeuda.DATAGLOBAL
import com.example.appconsultadeuda.DATAGLOBAL.Companion.prefs
import com.example.appconsultadeuda.Login
import com.example.appconsultadeuda.api.RecoveryPasswordApi
import com.example.appconsultadeuda.databinding.FrgChangePasswordBinding
import com.example.appconsultadeuda.entity.DataRecoveryPassword.dcConfirmCode
import com.example.appconsultadeuda.entity.DataRecoveryPassword.dcUpdatePassword
import com.unosoft.ecomercialapp.api.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class frgChangePassword : Fragment() {

    private var _binding: FrgChangePasswordBinding? = null
    private val binding get() = _binding!!

    var apiInterface: RecoveryPasswordApi? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?
    ): View {_binding = FrgChangePasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        eventsHandlets()
        iniciarBienvenida()
    }

    private fun iniciarBienvenida() {
        if (prefs.getName()!=""){
            binding.tvBienvenida.text = "BIENVENIDO ${prefs.getName()}"
            binding.tvMensaje.text = "Debe cambiar su contraseña por medida de seguridad"
        }
    }

    private fun eventsHandlets() {
        binding.btnConfirmarNewPasswords.setOnClickListener {
            if(binding.etNewPoss.length()>4 && binding.etRepeatNewPoss.length()>4){
                if (binding.etNewPoss.text.toString() == binding.etRepeatNewPoss.text.toString()){
                    changePasscode()
                }else{
                    Toast.makeText(activity, "LOS DATOS INGRESADOS NO SON IGUALES", Toast.LENGTH_SHORT).show()
                    binding.etNewPoss.setText("")
                    binding.etRepeatNewPoss.setText("")
                }
            }else{
                Toast.makeText(activity, "LA CANTIDAD MINIMA DE CARACTER ES 5", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun changePasscode() {
        val pd = ProgressDialog(activity)
        pd.setMessage("Actualizando datos....")
        pd.setCancelable(false)
        pd.create()
        pd.show()
        val NumeroDoc = dcUpdatePassword(binding.etNewPoss.text.toString(), prefs.getUser())
        apiInterface = APIClient.client!!.create(RecoveryPasswordApi::class.java)
        val call1 = apiInterface!!.postUpdatePassword(NumeroDoc)
        call1.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if(response.code()==200){
                    pd.cancel()
                    println("**********************")
                    println("Exito")
                    println("**********************")
                    Toast.makeText(activity, "SE CAMBIO LA CONTRASEÑA EXITOSAMENTE", Toast.LENGTH_SHORT).show()
                    CambiarFragment()
                }else{
                    pd.cancel()
                    println("**********************")
                    println("Error")
                    println("**********************")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                pd.cancel()
                Toast.makeText(activity, "VERIFICAR CONEXION", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun CambiarFragment() {
        val i = Intent(activity, Login::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(i)
        requireActivity().finish()
    }

}