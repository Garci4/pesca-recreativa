package com.example.pescarecreativa.modelo

import com.google.android.gms.maps.model.LatLng

data class Reporte (
    val titulo:String,
    val descripcion:String,
    val lugarCaptura:String,
    val fechaCaptura:String,
    val foto:String,
)