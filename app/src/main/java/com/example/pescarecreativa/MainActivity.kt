package com.example.pescarecreativa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.pescarecreativa.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val DURACION_SPLASHSCREEN : Long = 3000;

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


}