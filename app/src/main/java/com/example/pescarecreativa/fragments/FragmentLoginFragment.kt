package com.example.pescarecreativa.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.pescarecreativa.HomeActivity
import com.example.pescarecreativa.R


class FragmentLoginFragment : Fragment() {


  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val btn = view.findViewById<Button>(R.id.btnIniciarSesion)

        btn?.setOnClickListener {
            activity?.let {
              val intent = Intent (it, HomeActivity::class.java)
              it.startActivity(intent)
                print("asd")
            }
        }

        // Inflate the layout for this fragment
        return view


    }


}