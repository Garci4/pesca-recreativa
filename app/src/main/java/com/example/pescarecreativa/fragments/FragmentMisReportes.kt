package com.example.pescarecreativa.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pescarecreativa.R
import com.example.pescarecreativa.adapter.ReporteAdapter
import com.example.pescarecreativa.adapter.ReporteViewHolder
import com.example.pescarecreativa.databinding.ActivityHomeBinding
import com.example.pescarecreativa.modelo.ReporteService
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.firestore.FirebaseFirestore


class FragmentMisReportes : Fragment(), OnMapReadyCallback {
    private lateinit var db: FirebaseFirestore
    private lateinit var mMap: GoogleMap
    private lateinit var latlongMarcador: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        db = FirebaseFirestore.getInstance()
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_mis_reportes, container, false)

        val mapFragment = childFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

        return view
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        val rv = itemView.findViewById<RecyclerView>(R.id.rvMisReportes)
        rv.layoutManager = LinearLayoutManager(activity)
        // set the custom adapter to the RecyclerView
        val adapter = ReporteAdapter(ReporteService.obtenerReportes(db))
        rv.adapter = adapter

        adapter.setOnItemClickListener(object : ReporteAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                val activity = itemView!!.context as AppCompatActivity
                val fragmentDetalleReporte = FragmentDetalleReporte(ReporteService.listaReportesFirebase[position])
                activity.supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, fragmentDetalleReporte).addToBackStack(null).commit()
            }

        })

    }

    override fun onMapReady(googleMap: GoogleMap?) {
        var posicion: LatLng
        for (reporte in ReporteService.listaReportesFirebase) {
            posicion = getLatLong(reporte?.lugarCaptura)
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
    }

    private fun getLatLong(posicion: String): LatLng {
        var delimiter = "*"
        val parts = posicion.split(delimiter)
        return LatLng(parts[0].toDouble(), parts[1].toDouble())
    }



}