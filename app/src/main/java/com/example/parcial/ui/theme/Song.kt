package com.example.parcial.ui.theme

import android.app.Activity
import android.content.Intent
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

        // Obtener las referencias de los TextView
        binding.txtTitleSong.text = songName
        binding.txtInputEditSong.setText(songLyrics)

        // boton eliminar cancion
        binding.btnBorrar.setOnClickListener {
            val songName = intent.getStringExtra("songName")
            val songLyrics = intent.getStringExtra("songLyrics")
            Toast.makeText(this, "Canción eliminada", Toast.LENGTH_SHORT).show()

            // Crear intent para enviar de vuelta el nombre de la canción eliminada
            val intent = Intent()
            intent.putExtra("deletedSongName", songName)
            intent.putExtra("deletedSongLyrics", songLyrics)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        binding.btnEditar.setOnClickListener {
            val editSongName = intent.getStringExtra("edSongName")
            val editSongLyrics = intent.getStringExtra("edSongLyrics")
            val erraseSongName = intent.getStringExtra("SongName")
            val erraseSongLyrics = intent.getStringExtra("songLyrics")

            val newLyrics = binding.txtInputEditSong.text.toString()
            if (TextUtils.isEmpty(newLyrics)) {
                Toast.makeText(this, "No se puede dejar el campo vacío", Toast.LENGTH_SHORT).show()
            } else {
                // Crear un nuevo intent para abrir la actividad SaveSong y pasar los datos de la canción
                val editIntent = Intent(this, SaveSong::class.java)
                editIntent.putExtra("edSongName", editSongName)
                editIntent.putExtra("edSongLyrics", editSongLyrics)
                editIntent.putExtra("songName", erraseSongName)
                editIntent.putExtra("songLyrics", erraseSongLyrics)
                startActivityForResult(editIntent, 3)
            }
        }

    }
}