package com.example.parcial.ui.theme

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.parcial.databinding.SaveLetterBinding

class SaveSong : AppCompatActivity() {
    private lateinit var binding: SaveLetterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SaveLetterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnSaveSong.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)

            //trayendo los valores de los campos de texto
            val songName = binding.txtInputEditName.text.toString()
            val songLyrics = binding.txtInputEditLetter.text.toString()

            //guardando los valores y mostrandolos en consola y en la MainActivity viewList
            println("Nombre de la canción: $songName")
            println("Letra de la canción: $songLyrics")
            intent.putExtra("songName", songName)
            intent.putExtra("songLyrics", songLyrics)
            Toast.makeText(this, "Canción guardada", Toast.LENGTH_SHORT).show()
            setResult(RESULT_OK, intent)
            finish()
        }

        binding.btnReturn.setOnClickListener {
            finish()
        }
    }
}