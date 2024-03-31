package com.example.parcial.ui.theme

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.parcial.R
import com.example.parcial.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var editTextSearch: EditText
    private lateinit var listViewSongs: ListView
    private lateinit var songAdapter: ArrayAdapter<String>
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

        //allSongs.addAll(listOf("Canción 1", "Canción 2", "Canción 3", "Canción 4"))
        filteredSongs.addAll(allSongs)

        // Inicializar ListView
        songAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, filteredSongs)
        listViewSongs.adapter = songAdapter

        listViewSongs.setOnItemClickListener { parent, view, position, id ->
            val selectedSong = parent.getItemAtPosition(position) as String
            val songLyrics = allSongs[position]  // Obtén las letras de la canción según la posición en la lista

            // Crear intent para abrir la actividad SongActivity
            val intent = Intent(this, Song::class.java)

            // Pasar datos de la canción a la actividad SongActivity
            intent.putExtra("songName", selectedSong)
            intent.putExtra("songLyrics", songLyrics)

            // Iniciar la actividad SongActivity
            startActivityForResult(intent, 2) // Cambiado de `startActivity` a `startActivityForResult`
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
                val songLyrics = data?.getStringExtra("songLyrics")
                if (songName != null && songLyrics != null) {
                    allSongs.add(songLyrics)
                    filteredSongs.add(songName)
                    songAdapter.notifyDataSetChanged()
                    Toast.makeText(this, "Canción agregada: $songName", Toast.LENGTH_SHORT).show()
                }
            } else if (requestCode == 2) { // Eliminar canción
                val deletedSongName = data?.getStringExtra("deletedSongName")
                if (!deletedSongName.isNullOrEmpty()) {
                    // Elimina la canción de la lista
                    allSongs.remove(deletedSongName)
                    filteredSongs.remove(deletedSongName)
                    songAdapter.notifyDataSetChanged()
                    Toast.makeText(this, "Canción eliminada: $deletedSongName", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun filterSongs(query: String) {
        filteredSongs.clear()
        if (query.isEmpty()) {
            filteredSongs.addAll(allSongs)
        } else {
            for (song in allSongs) {
                if (song.contains(query, ignoreCase = true)) {
                    filteredSongs.add(song)
                }
            }
        }
        songAdapter.notifyDataSetChanged()
    }
}