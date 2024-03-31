package com.example.parcial.logic

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.parcial.R
import com.example.parcial.databinding.ActivityMaracaBinding
import com.example.parcial.ui.theme.MainActivity

class Maraca : AppCompatActivity() {
    private lateinit var binding: ActivityMaracaBinding

    private lateinit var mediaPlayer: MediaPlayer

    val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMaracaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mediaPlayer = MediaPlayer.create(this, R.raw.tambor)

        

        binding.btnCancelMaraca.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}