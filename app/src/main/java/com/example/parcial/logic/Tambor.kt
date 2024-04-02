package com.example.parcial.logic

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.parcial.R
import com.example.parcial.databinding.ActivityTamborBinding
import com.example.parcial.ui.theme.MainActivity
import android.media.MediaPlayer

class Tambor : AppCompatActivity() {

    private lateinit var binding: ActivityTamborBinding
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityTamborBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mediaPlayer = MediaPlayer.create(this, R.raw.tambor)

        binding.btnPlayTambor.setOnClickListener {
            mediaPlayer.seekTo(100)

            mediaPlayer.start()
        }

        binding.btnCancelTambor.setOnClickListener {
            finish()
        }
    }
}