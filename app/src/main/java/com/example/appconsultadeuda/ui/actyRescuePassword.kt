package com.example.appconsultadeuda.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appconsultadeuda.R
import com.example.appconsultadeuda.databinding.ActyRescuePasswordBinding

class actyRescuePassword : AppCompatActivity() {

    private lateinit var binding : ActyRescuePasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActyRescuePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initFragmente()

    }

    private fun initFragmente() {
        val fragmento = supportFragmentManager.beginTransaction()
        fragmento.replace(R.id.fg_recuepassword,frgEnterDoc())
        fragmento.commit()
    }
}