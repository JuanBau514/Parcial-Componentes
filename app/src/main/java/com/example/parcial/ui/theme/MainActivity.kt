package com.example.parcial.ui.theme

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.parcial.R
import com.example.parcial.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var editTextSearch: EditText
    private lateinit var listViewSongs: ListView
    private lateinit var songAdapter: ArrayAdapter<String>
    private val songNames = mutableListOf<String>() // Lista para almacenar los nombres de las canciones
    private val songLyrics = mutableListOf<String>() // Lista para almacenar las letras de las canciones
    private val allSongs = mutableListOf<String>()
    private val filteredSongs = mutableListOf<String>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar vistas
        editTextSearch = findViewById(R.id.txtSearch)
        listViewSongs = findViewById(R.id.listViewSongs)

        // Inicializar ListView
        songAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, filteredSongs)
        listViewSongs.adapter = songAdapter

        editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                filterSongs(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        listViewSongs.setOnItemClickListener { parent, view, position, id ->
            val selectedSong = filteredSongs[position]
            val songLyrics = songLyrics[songNames.indexOf(selectedSong)]

            // Crear intent para abrir la actividad Song
            val intent = Intent(this, Song::class.java)
            intent.putExtra("songName", selectedSong)
            intent.putExtra("songLyrics", songLyrics)
            startActivityForResult(intent, 2) // Usar startActivityForResult() en lugar de startActivity()
        }

        binding.btnSave.setOnClickListener{
            var intent = Intent(this, SaveSong::class.java)
            startActivityForResult(intent, 1)
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


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1) { // Agregar canción
                val songName = data?.getStringExtra("songName")
                val songLyric = data?.getStringExtra("songLyrics")
                if (songName != null && songLyric != null) {
                    songNames.add(songName)
                    songLyrics.add(songLyric)
                    filteredSongs.add(songName)
                    songAdapter.notifyDataSetChanged()
                    Toast.makeText(this, "Canción agregada: $songName", Toast.LENGTH_SHORT).show()
                }
            } else if (requestCode == 2) { // Eliminar canción desde Song
                val deletedSongName = data?.getStringExtra("deletedSongName")
                val deletedSongLyrics = data?.getStringExtra("deletedSongLyrics")
                if (!deletedSongName.isNullOrEmpty()) {
                    Toast.makeText(this, "Canción eliminada: $deletedSongName", Toast.LENGTH_SHORT).show()
                    val index = songNames.indexOf(deletedSongName)
                    if (index != -1) { // Verificar si se encontró la canción
                        songNames.removeAt(index)
                        songLyrics.removeAt(index)
                        filteredSongs.remove(deletedSongName)
                        songAdapter.notifyDataSetChanged()
                        Toast.makeText(this, "Canción eliminada: $deletedSongName", Toast.LENGTH_SHORT).show()
                    }
                }
            } else if (requestCode == 3) { // Editar canción desde Song
                val editedSongName = data?.getStringExtra("editedSongName")
                val editedSongLyrics = data?.getStringExtra("editedSongLyrics")
                if (!editedSongName.isNullOrEmpty() && !editedSongLyrics.isNullOrEmpty()) {
                    val index = songNames.indexOf(editedSongName)
                    if (index != -1) { // Verificar si se encontró la canción
                        songLyrics[index] = editedSongLyrics
                        songAdapter.notifyDataSetChanged()
                        Toast.makeText(this, "Canción editada: $editedSongName", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
    }
    private fun filterSongs(query: String) {
        filteredSongs.clear()
        if (query.isEmpty()) {
            filteredSongs.addAll(songNames)
        } else {
            for (songName in songNames) {
                if (songName.contains(query, ignoreCase = true)) {
                    filteredSongs.add(songName)
                }
            }
        }
        songAdapter.notifyDataSetChanged()
    }

}