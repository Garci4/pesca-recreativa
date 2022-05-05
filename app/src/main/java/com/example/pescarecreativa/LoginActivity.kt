package com.example.pescarecreativa

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout


class LoginActivity : AppCompatActivity() {

    private val editUsuario: EditText? = findViewById(R.id.editUsuario);
    private var editPassword:EditText? = findViewById(R.id.editPassword);
    private val btnIniciarSesion: Button? = findViewById(R.id.btnIniciarSesion);
    private val txtInputUsuario: TextInputLayout? = findViewById(R.id.txtInputUsuario);
    private  var txtInputPassword:TextInputLayout? = findViewById(R.id.txtInputPassword);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

}