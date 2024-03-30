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

        // Agregar canciones de ejemplo (puedes cargar las canciones desde cualquier fuente)
        allSongs.addAll(listOf("Canción 1", "Canción 2", "Canción 3", "Canción 4"))
        filteredSongs.addAll(allSongs)

        // Inicializar adaptador para el ListView
        songAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, filteredSongs)
        listViewSongs.adapter = songAdapter

        editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                filterSongs(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.btnSave.setOnClickListener{
            var intent = Intent(this, SaveSong::class.java)
            startActivity(intent)
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