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
import com.example.pescarecreativa.databinding.ActivityHomeBinding
import com.example.pescarecreativa.databinding.FragmentDetalleReporteBinding
import com.example.pescarecreativa.modelo.Reporte
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class FragmentDetalleReporte(rep: Reporte) : Fragment(), GoogleMap.OnMapClickListener,
    OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    val reporte = rep

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = inflater.inflate(R.layout.fragment_detalle_reporte, container, false)
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetalleReporteBinding.bind(view)
        binding.tvDetalleReporteTitulo.text = reporte.titulo
        binding.tvDetalleReporteDescripcion.text = reporte.descripcion
        binding.tvDetalleReporteLugarCaptura.text = reporte.lugarCaptura
        Glide.with(binding.ivDetalleReporte.context).load(reporte.foto).into(binding.ivDetalleReporte)
    }

    override fun onMapClick(p0: LatLng?) {
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        var posicion = getLatLong(reporte?.lugarCaptura)
        if (googleMap != null) {
            mMap = googleMap
        }
        mMap.addMarker(
            MarkerOptions().position(posicion).title(reporte.titulo)
            .snippet(reporte.descripcion)
            .rotation(-15.0f)
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(posicion));

    }

    private fun getLatLong(posicion: String): LatLng {
        var delimiter = "*"
        val parts = posicion.split(delimiter)
        return LatLng(parts[0].toDouble(), parts[1].toDouble())
    }


}