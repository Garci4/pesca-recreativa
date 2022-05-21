package com.example.pescarecreativa.adapter

import android.view.TextureView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pescarecreativa.R
import com.example.pescarecreativa.modelo.Reporte

class ReporteViewHolder(view: View):RecyclerView.ViewHolder(view) {

    val reporteTitulo = view.findViewById<TextView>(R.id.tvReporteTitulo)
    val reporteDescripcion = view.findViewById<TextView>(R.id.tvReporteDescripcion)
    val reporteLugarCaptura = view.findViewById<TextView>(R.id.tvReporteLugarCaptura)
    val reporteFoto = view.findViewById<ImageView>(R.id.ivReporte)

    fun render(reporte: Reporte){
        reporteTitulo.text = reporte.titulo
        reporteDescripcion.text = reporte.descripcion
        reporteLugarCaptura.text = reporte.lugarCaptura
    }

}