package com.example.parcial.ui.theme

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
            binding.btnBorrar.setOnClickListener {
                val songName = intent.getStringExtra("songName")
                val songLyrics = intent.getStringExtra("songLyrics")

                // Crear intent para enviar de vuelta el nombre y la letra de la canción eliminada
                val intent = Intent()
                intent.putExtra("deletedSongName", songName)
                intent.putExtra("deletedSongLyrics", songLyrics)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }

        binding.btnEditar.setOnClickListener {
            val songName = intent.getStringExtra("songName")
            val songLyrics = intent.getStringExtra("songLyrics")

            // Crear intent para enviar de vuelta el nombre y la letra de la canción editada
            val intent = Intent()
            intent.putExtra("editedSongName", songName)
            intent.putExtra("editedSongLyrics", binding.txtInputEditSong.text.toString())
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

    }
}