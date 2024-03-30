package com.example.parcial.ui.theme

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.SearchView
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

        editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                filterSongs(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        listViewSongs.setOnItemClickListener { parent, view, position, id ->
            val selectedSong = parent.getItemAtPosition(position) as String
            val songLyrics = allSongs[position]  // Obtén las letras de la canción según la posición en la lista

            // Crear intent para abrir la actividad SongActivity
            val intent = Intent(this, Song::class.java)

            // Pasar datos de la canción a la actividad SongActivity
            intent.putExtra("songName", selectedSong)
            intent.putExtra("songLyrics", songLyrics)

            // Iniciar la actividad SongActivity
            startActivity(intent)
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