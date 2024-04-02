package com.example.parcial.logic

import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.parcial.R
import com.example.parcial.databinding.ActivityMaracaBinding
import com.example.parcial.ui.theme.MainActivity

class Maraca : AppCompatActivity(), SensorEventListener {
    private lateinit var binding: ActivityMaracaBinding

    private lateinit var mediaPlayer: MediaPlayer

    private lateinit var sensorManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {

        configSensor()

        super.onCreate(savedInstanceState)

        binding= ActivityMaracaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mediaPlayer = MediaPlayer.create(this, R.raw.maraca)
        mediaPlayer.isLooping = true

        binding.btnCancelMaraca.setOnClickListener {
            sensorManager.unregisterListener(this)
            mediaPlayer.release()
            finish()
        }
    }

    private fun configSensor(){
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)?.also{
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL,
                SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
            if(event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
                val accelerationX = event.values[0]
                val accelerationY = event.values[1]
                val accelerationz = event.values[2]

                val acceleration =
                    Math.sqrt((accelerationX * accelerationX + accelerationY * accelerationY + accelerationz * accelerationz).toDouble())
                val maxAcceleration = 10.0

                if (acceleration > maxAcceleration) {
                    mediaPlayer.start()
                } else {
                    mediaPlayer.pause()
                }
            }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
    override fun onDestroy() {
        super.onDestroy()
        sensorManager.unregisterListener(this)
        mediaPlayer.release()
    }
}