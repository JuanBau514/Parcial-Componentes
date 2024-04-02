package com.example.parcial.logic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.parcial.R
import com.example.parcial.databinding.ActivityTamborBinding
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