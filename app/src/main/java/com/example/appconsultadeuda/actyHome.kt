package com.example.appconsultadeuda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appconsultadeuda.databinding.ActyHomeBinding

class actyHome : AppCompatActivity() {
    private lateinit var binding : ActyHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActyHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}