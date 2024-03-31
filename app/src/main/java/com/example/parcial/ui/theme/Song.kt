package com.example.parcial.ui.theme

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.parcial.R
import com.example.parcial.databinding.ActivitySongBinding

class Song : AppCompatActivity() {


    private lateinit var binding: ActivitySongBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recuperar los datos de la canción del Intent
        val songName = intent.getStringExtra("songName")
        val songLyrics = intent.getStringExtra("songLyrics")

        Toast.makeText(this, "Nombre de la canción: $songName", Toast.LENGTH_SHORT).show()
        Toast.makeText(this, "Letra de la canción: $songLyrics", Toast.LENGTH_SHORT).show()

        // Obtener las referencias de los TextView
        binding.txtTitleSong.text = songName
        binding.txtInputEditSong.setText(songLyrics)
    }
}