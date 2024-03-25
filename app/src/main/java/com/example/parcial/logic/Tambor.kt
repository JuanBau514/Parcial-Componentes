package com.example.parcial.logic

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.parcial.R
import com.example.parcial.databinding.ActivityRulesBinding
import com.example.parcial.databinding.ActivityTamborBinding
import com.example.parcial.ui.theme.MainActivity

class Tambor : AppCompatActivity() {

    private lateinit var binding: ActivityTamborBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityTamborBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCancelTambor.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}