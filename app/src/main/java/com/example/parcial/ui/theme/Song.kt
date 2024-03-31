package com.example.parcial.ui.theme

import android.os.Bundle
import android.widget.TextView
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

        // Obtener las referencias de los TextView
        val mostrarNombre = findViewById<TextView>(R.id.txtTitleSong)
        val mostrarLetra = findViewById<TextView>(R.id.txtInputEditSong)

        // Establecer el texto en los TextView
        mostrarNombre.text = songName
        mostrarLetra.text = songLyrics
    }
}