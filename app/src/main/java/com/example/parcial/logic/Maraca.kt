package com.example.parcial.logic

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.parcial.R
import com.example.parcial.databinding.ActivityMaracaBinding
import com.example.parcial.ui.theme.MainActivity

class Maraca : AppCompatActivity() {
    private lateinit var binding: ActivityMaracaBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMaracaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCancelMaraca.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}