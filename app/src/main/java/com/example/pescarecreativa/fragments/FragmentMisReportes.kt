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
import com.example.pescarecreativa.modelo.ReporteService
import com.google.firebase.firestore.FirebaseFirestore


class FragmentMisReportes : Fragment() {
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        db = FirebaseFirestore.getInstance()
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_mis_reportes, container, false)
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


}