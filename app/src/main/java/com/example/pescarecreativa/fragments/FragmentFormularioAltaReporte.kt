package com.example.pescarecreativa.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.pescarecreativa.HomeActivity
import com.example.pescarecreativa.R
import com.example.pescarecreativa.modelo.Reporte
import com.example.pescarecreativa.modelo.ReporteService

class FragmentFormularioAltaReporte : Fragment() {

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle? ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_formulario_alta_reporte, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btn = view.findViewById<Button>(R.id.boton_aceptar)
        btn.setOnClickListener(View.OnClickListener { view2 ->
            val titulo:String = view.findViewById<EditText>(R.id.editTitulo).text.toString()
            val descripcion = view.findViewById<EditText>(R.id.editDescripcion).text.toString()
            val lugarCaptura = view.findViewById<EditText>(R.id.editLugarCaptura).text.toString()
            val foto = view.findViewById<EditText>(R.id.editFoto).text.toString()
            val reporte = Reporte(titulo = titulo, descripcion = descripcion, lugarCaptura = lugarCaptura, foto = foto)
            ReporteService.listaReportes = ReporteService.listaReportes + reporte
            activity?.let {
                val intent = Intent (it, HomeActivity::class.java)
                it.startActivity(intent)
                print("asd")
            }
        })
    }
}