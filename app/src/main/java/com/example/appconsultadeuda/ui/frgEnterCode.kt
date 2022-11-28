package com.example.appconsultadeuda.ui

import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.test.core.app.ActivityScenario.launch
import com.example.appconsultadeuda.DATAGLOBAL.Companion.prefs
import com.example.appconsultadeuda.R
import com.example.appconsultadeuda.api.RecoveryPasswordApi
import com.example.appconsultadeuda.databinding.FrgEnterCodeBinding
import com.example.appconsultadeuda.entity.DataRecoveryPassword.dcConfirmCode
import com.example.appconsultadeuda.entity.DataRecoveryPassword.dcRecoveryPassword
import com.example.appconsultadeuda.entity.DataRecoveryPassword.dcResendCode
import com.unosoft.ecomercialapp.api.APIClient
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class frgEnterCode : Fragment() {
    private var _binding: FrgEnterCodeBinding? = null
    private val binding get() = _binding!!

    var apiInterface: RecoveryPasswordApi? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?
    ): View {_binding = FrgEnterCodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eventsHandlers()
        activeTextReenviarCode()
    }

    private fun activeTextReenviarCode() {
        binding.tvReenviarCode.isEnabled = false
        CoroutineScope(Dispatchers.IO).launch{
            for (i in 90 downTo 1) {
                activity?.runOnUiThread{
                    binding.tvReenviarCode.text = "Reenviar codigo de verificación (${i}s)"
                }
                delay(1000)
            }
            activity?.runOnUiThread{
                binding.tvReenviarCode.text = "Reenviar codigo de verificación"
                binding.tvReenviarCode.isEnabled = true
                binding.tvReenviarCode.setOnClickListener { reenviarCodigo() }
            }
        }

    }

    private fun reenviarCodigo() {

        val pd = ProgressDialog(activity)
        pd.setMessage("Reenviando codigo....")
        pd.setCancelable(false)
        pd.create()
        pd.show()

        val NumeroDoc = dcResendCode(prefs.getUser())
        apiInterface = APIClient.client!!.create(RecoveryPasswordApi::class.java)
        val call1 = apiInterface!!.postResendCode(NumeroDoc)
        call1.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if(response.code()==200){
                    pd.cancel()
                    activeTextReenviarCode()
                    Toast.makeText(activity, "SE REENVIO CODIGO", Toast.LENGTH_SHORT).show()
                }else{
                    pd.cancel()
                    println("**********************")
                    println("Error")
                    println("**********************")
                    Toast.makeText(activity, "DATOS INVALIDOS", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                pd.cancel()
                Toast.makeText(activity, "VERIFICAR CONEXION", Toast.LENGTH_SHORT).show()
            }

        })

    }

    private fun eventsHandlers() {
        binding.btnEnterCode.setOnClickListener {
            enterDode()
             }
    }

    private fun enterDode() {
        val pd = ProgressDialog(activity)
        pd.setMessage("Validando datos....")
        pd.setCancelable(false)
        pd.create()
        pd.show()
        val NumeroDoc = dcConfirmCode(binding.etEnterCode.text.toString(), prefs.getUser())
        apiInterface = APIClient.client!!.create(RecoveryPasswordApi::class.java)
        val call1 = apiInterface!!.postEnterCode(NumeroDoc)
        call1.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if(response.code()==200){
                    pd.cancel()
                    println("**********************")
                    println("Exito")
                    println("**********************")
                    prefs.save_Name("")
                    CambiarFragment()
                }else{
                    pd.cancel()
                    println("**********************")
                    println("Error")
                    println("**********************")
                    Toast.makeText(activity, "DATOS INVALIDOS", Toast.LENGTH_SHORT).show()
                }

            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                pd.cancel()
                Toast.makeText(activity, "VERIFICAR CONEXION", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun CambiarFragment() {
        val fragmento = requireActivity().supportFragmentManager.beginTransaction()
        fragmento.replace(R.id.fg_recuepassword,frgChangePassword())
        fragmento.commit()
    }
}