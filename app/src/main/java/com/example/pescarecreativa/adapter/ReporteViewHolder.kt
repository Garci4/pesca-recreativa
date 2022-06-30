package com.example.pescarecreativa.adapter

import android.content.ContentValues
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pescarecreativa.R
import com.example.pescarecreativa.databinding.ReporteItemBinding
import com.example.pescarecreativa.fragments.FragmentDetalleReporte
import com.example.pescarecreativa.modelo.Reporte

class ReporteViewHolder(view: View, listener: ReporteAdapter.onItemClickListener):RecyclerView.ViewHolder(view) {

    val binding = ReporteItemBinding.bind(view)

    init {
        view.setOnClickListener {
            listener.onItemClick(bindingAdapterPosition)
        }
    }

    fun render(reporte: Reporte){
        Log.d(ContentValues.TAG, "////reporte: ${reporte}")
        binding.tvReporteTitulo.text = reporte.titulo
        binding.tvReporteDescripcion.text = reporte.descripcion
        binding.tvReporteFechaCaptura.text = reporte.fechaCaptura
        Glide.with(binding.ivReporte.context).load(reporte.foto).into(binding.ivReporte)
    }
}