package com.example.appconsultadeuda.ui

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appconsultadeuda.R
import com.example.appconsultadeuda.databinding.ActyCambioContrasenaBinding
import com.example.appconsultadeuda.databinding.CardInputCodeBinding

class actyCambioContrasena : AppCompatActivity() {
    private lateinit var binding : ActyCambioContrasenaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActyCambioContrasenaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        eventsHandlers()
    }

    private fun eventsHandlers() {
        binding.btnConfirmar.setOnClickListener { verificarDatos()}
    }

    private fun verificarDatos() {
        if (binding.tvOldPasscode.text.toString() == "" ||
            binding.tvNewPasscode.text.toString() == "" ||
            binding.tvNewPasscodeRepirt.text.toString() == "" ){
            AlertMessage("Informacion Invalida")
        }else{
            val builder = AlertDialog.Builder(this@actyCambioContrasena)
            val vista = layoutInflater.inflate(R.layout.card_input_code, null)
            val binding2 = CardInputCodeBinding.bind(vista)
            vista.setBackgroundResource(R.color.transparent)

            //Pasar vista al buielder
            builder.setView(vista)
            //*********************************************

            val dialog = builder.create()

            dialog.show()

            binding2.btVerificar.setOnClickListener {
                dialog.dismiss()
            }
        }
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