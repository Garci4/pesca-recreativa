package com.example.pescarecreativa.fragments

import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.pescarecreativa.R
import com.example.pescarecreativa.databinding.FragmentDetalleReporteBinding
import com.example.pescarecreativa.modelo.Reporte

class FragmentDetalleReporte(rep: Reporte) : Fragment() {

    val reporte = rep

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalle_reporte, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDetalleReporteBinding.bind(view)
        binding.tvDetalleReporteTitulo.text = reporte.titulo
        binding.tvDetalleReporteDescripcion.text = reporte.descripcion
        binding.tvDetalleReporteLugarCaptura.text = reporte.lugarCaptura
        Glide.with(binding.ivDetalleReporte.context).load(reporte.foto).into(binding.ivDetalleReporte)

    }


}