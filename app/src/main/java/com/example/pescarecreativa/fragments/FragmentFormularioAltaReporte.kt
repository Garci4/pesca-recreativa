package com.example.pescarecreativa.fragments

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import com.example.pescarecreativa.HomeActivity
import com.example.pescarecreativa.R
import com.example.pescarecreativa.modelo.Reporte
import com.example.pescarecreativa.modelo.ReporteService
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class FragmentFormularioAltaReporte : Fragment() {

    private lateinit var db: FirebaseFirestore

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle? ): View? {
        // Inflate the layout for this fragment
        db = FirebaseFirestore.getInstance()
        return inflater.inflate(R.layout.fragment_formulario_alta_reporte, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btn = view.findViewById<Button>(R.id.boton_aceptar)
        var cal = Calendar.getInstance()
        val fechaCaptura = view.findViewById<EditText>(R.id.editFechaCaptura)
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val myFormat = "MM/dd/yyyy" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                fechaCaptura!!.setText(sdf.format(cal.getTime()))
            }
        }
        fechaCaptura.setOnClickListener(object : View.OnClickListener {
                override fun onClick(view: View) {
                    DatePickerDialog(requireContext(),
                        dateSetListener,
                        // set DatePickerDialog to point to today's date when it loads up
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)).show()
                }
        })

        btn.setOnClickListener(View.OnClickListener { view2 ->
            val titulo:String = view.findViewById<EditText>(R.id.editTitulo).text.toString()
            val descripcion = view.findViewById<EditText>(R.id.editDescripcion).text.toString()
            val lugarCaptura = view.findViewById<EditText>(R.id.editLugarCaptura).text.toString()
            val foto = view.findViewById<EditText>(R.id.editFoto).text.toString()
            val reporte = Reporte(titulo = titulo, descripcion = descripcion, lugarCaptura = lugarCaptura, foto = foto, fechaCaptura = fechaCaptura.text.toString())
            ReporteService.listaReportes = ReporteService.listaReportes + reporte
            ReporteService.agregarReporte(db, reporte)
            activity?.let {
                val intent = Intent (it, HomeActivity::class.java)
                it.startActivity(intent)
                print("asd")
            }
        })
    }
}