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
import com.example.appconsultadeuda.R
import com.example.appconsultadeuda.api.RecoveryPasswordApi
import com.example.appconsultadeuda.databinding.FrgEnterDocBinding
import com.example.appconsultadeuda.entity.DataRecoveryPassword.dcRecoveryPassword
import com.unosoft.ecomercialapp.api.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class frgEnterDoc : Fragment() {
    private var _binding:FrgEnterDocBinding? = null
    private val binding get() = _binding!!

    var apiInterface: RecoveryPasswordApi? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?
    ): View {_binding = FrgEnterDocBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eventsHandlers()
    }

    private fun eventsHandlers() {
        binding.btnEnviar.setOnClickListener {
            enviarDoc()
        }



    }

    private fun enviarDoc() {
        val pd = ProgressDialog(activity)
        pd.setMessage("Validando datos....")
        pd.setCancelable(false)
        pd.create()
        pd.show()

        val NumeroDoc = dcRecoveryPassword(binding.etEnterDoc.text.toString())
        apiInterface = APIClient.client!!.create(RecoveryPasswordApi::class.java)
        val call1 = apiInterface!!.postEnterDoc(NumeroDoc)
        call1.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if(response.code()==200){
                    pd.cancel()
                    prefs.save_User(binding.etEnterDoc.text.toString())
                    println("**********************")
                    println("Exito")
                    println("**********************")
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
        fragmento.replace(R.id.fg_recuepassword,frgEnterCode())
        fragmento.commit()
    }





}