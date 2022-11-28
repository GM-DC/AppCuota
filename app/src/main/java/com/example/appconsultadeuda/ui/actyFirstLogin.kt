package com.example.appconsultadeuda.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appconsultadeuda.R
import com.example.appconsultadeuda.databinding.ActyRescuePasswordBinding

class actyFirstLogin : AppCompatActivity() {

    private lateinit var binding : ActyRescuePasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActyRescuePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initFragmente()
    }

    private fun initFragmente() {
        val fragmento = supportFragmentManager.beginTransaction()
        fragmento.replace(R.id.fg_recuepassword,frgChangePassword())
        fragmento.commit()
    }
}