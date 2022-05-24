package com.example.pescarecreativa.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.pescarecreativa.HomeActivity
import com.example.pescarecreativa.R
import com.example.pescarecreativa.modelo.UsuarioService
import com.google.android.material.textfield.TextInputEditText


class FragmentLoginFragment : Fragment() {


  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val btn = view.findViewById<Button>(R.id.btnIniciarSesion)

        btn?.setOnClickListener {
            activity?.let {
                val user = view.findViewById<TextInputEditText>(R.id.editUsuario)
                val password = view.findViewById<TextInputEditText>(R.id.editPassword)
                val tieneAcceso: Boolean = UsuarioService.esUsuarioValido(user.text.toString(), password.text.toString())
                if (tieneAcceso) {
                    val intent = Intent (it, HomeActivity::class.java)
                    it.startActivity(intent)
                }
            }
        }

        // Inflate the layout for this fragment
        return view


    }


}