package com.example.parcial.ui.theme

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.parcial.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener{
            var intent = Intent(this, SaveSong::class.java)
            startActivity(intent)
        }

        binding.btnMaraca.setOnClickListener{
            var intent = Intent(this, Rules::class.java)
            intent.putExtra("instrument", "Maraca")
            startActivity(intent)
        }

        binding.btnTambor.setOnClickListener{
            var intent = Intent(this, Rules::class.java)
            intent.putExtra("instrument", "Tambor")
            startActivity(intent)
        }
    }
}