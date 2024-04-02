package com.example.parcial.ui.theme

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.parcial.R
import com.example.parcial.databinding.ActivityRulesBinding
import com.example.parcial.databinding.ActivitySongBinding
import com.example.parcial.logic.Maraca
import com.example.parcial.logic.Tambor


class Rules : AppCompatActivity() {

    private lateinit var binding: ActivityRulesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRulesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val instrument = intent.getStringExtra("instrument")
        val textView: TextView = findViewById(R.id.txtBodyRules)

        if (instrument=="Maraca"){
            textView.text = "Para practicar el instrumento de la maraca, agita tu teléfono para " +
                    "producir su sonido característico. Sin embargo, ten cuidado al hacerlo, ya que" +
                    " existe el riesgo de que el dispositivo se caiga al suelo y se dañe."
            binding.btnStartRules.setOnClickListener {
                var intent = Intent(this, Maraca::class.java)
                startActivity(intent)
                finish()
            }
        }else{
            if(instrument=="Tambor"){
                textView.text = "Para practicar el instrumento del tambor, toca la pantalla para " +
                        "producir su sonido característico. Recuerda que el sonido producido depende" +
                        " de qué tan rápido toques el táctil del dispositivo."
                binding.btnStartRules.setOnClickListener {
                    var intent = Intent(this, Tambor::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }


        binding.btnCancelRules.setOnClickListener {
            finish()
        }

    }
}