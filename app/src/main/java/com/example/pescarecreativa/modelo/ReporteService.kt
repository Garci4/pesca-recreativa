package com.example.pescarecreativa.modelo

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.ktx.firestore

class ReporteService {
    companion object{
        var listaReportes = listOf<Reporte>(
            Reporte("Captura 1", "Descripcion de la captura 1", "Playa Union", "https://diariolaportada.com.ar/wp-content/uploads/2022/04/pesca-3.jpg", "06/06/2022"),
            Reporte("Captura 2", "Descripcion de la captura 2", "Santa Isabel", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTSdiielKiJjpT9LlmyMlGucWSfwUZxdqShrw&usqp=CAU", "06/06/2022"),
            Reporte("Captura 4", "Descripcion de la captura 4", "Playa Union", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQeVN5D4IIf6VypLRn7cryvCsMlGRXKXp9cMg&usqp=CAU", "06/06/2022"),
            Reporte("Captura 3", "Descripcion de la captura 3", "Los palos", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSHuMzRASp9qc8E-1ICvHaRxyx8vARjgu5w8w&usqp=CAU", "06/06/2022"),
            Reporte("Captura 5", "Descripcion de la captura 5", "Playa Union", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR-QP5fuVpKwQImM9pHqjmmXCsX4A-mfozhkw&usqp=CAU", "06/06/2022"),
            Reporte("Captura 6", "Descripcion de la captura 6", "Los palos", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRyxofl-kpY3wxqBOHBI5UsyXWyU67AmKjtTQ&usqp=CAU", "06/06/2022"),
            Reporte("Captura 7", "Descripcion de la captura 7", "Pto Madryn", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRA7RxGnLkDdYpBqNfIBvkT3_edoxaxUv1jnQ&usqp=CAU", "06/06/2022"),
            Reporte("Captura 8", "Descripcion de la captura 8", "Pto Madryn", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTL1NWjrUAzpRzoat3eGvqJrr-6AxqmEh4Y6g&usqp=CAU", "06/06/2022"),
            Reporte("Captura 9", "Descripcion de la captura 9", "Pto Madryn", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTCsVXRe5tO6F9W3k2SBnaMhtnoGG03ijRBWg&usqp=CAU", "06/06/2022"),
            Reporte("Captura 10", "Descripcion de la captura 10", "Playa Union", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTwrgKts4wD-4bOEMJ3qKD_kCict_1QNTf2fQ&usqp=CAU", "06/06/2022")
        )

        fun agregarReporte(db: FirebaseFirestore, reporte: Reporte) {
            val r = hashMapOf(
                "titulo" to reporte.titulo,
                "descripcion" to reporte.descripcion,
                "lugarCaptura" to reporte.lugarCaptura,
                "fechaCaptura" to reporte.lugarCaptura,
                "foto" to reporte.foto,
            )
            db.collection("reporte")
                .add(r)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "guardado con exito")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "error en guardado", e)                }
        }
    }
}