package com.example.pescarecreativa.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.pescarecreativa.R
import com.example.pescarecreativa.fragments.FragmentDetalleReporte
import com.example.pescarecreativa.modelo.Reporte

class ReporteAdapter(private val reporteList:List<Reporte>) :
    RecyclerView.Adapter<ReporteViewHolder>()
{

    private lateinit var mListener : onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    //el view holder itera en el tamanio de la lista y va pintando todos los atributos de cada objeto de la lista
    //se le debe pasar el "item" o layout que debe ir modificando segun los atributos del objeto
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReporteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.reporte_item, parent, false)

        return ReporteViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: ReporteViewHolder, position: Int) {
        val item = reporteList[position]

        holder.render(item)
    }

    //El entero que devuelve debe ser el tambaño del listado que vamos a mostrar
    /*
    * En kotlin es lo mismo:
    *   override fun getItemCount(): Int { .... return intVariable }
    * que:
    *   override fun getItemCount(): Int = intVariable
    * */
    override fun getItemCount(): Int = reporteList.size
}