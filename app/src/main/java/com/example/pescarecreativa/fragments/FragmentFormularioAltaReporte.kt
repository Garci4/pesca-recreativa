package com.example.pescarecreativa.fragments

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.pescarecreativa.HomeActivity
import com.example.pescarecreativa.R
import com.example.pescarecreativa.databinding.ActivityHomeBinding
import com.example.pescarecreativa.modelo.Reporte
import com.example.pescarecreativa.modelo.ReporteService
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.io.ByteArrayOutputStream
import java.net.URI
import java.text.SimpleDateFormat
import java.util.*

class FragmentFormularioAltaReporte : Fragment(), GoogleMap.OnMapClickListener, OnMapReadyCallback {

    private lateinit var db: FirebaseFirestore
    private lateinit var storage: FirebaseStorage
    private lateinit var view_global: View

    private var filePath: Uri? = null
    private var storageReference: StorageReference? = null

    private var urlImagenEnFireStorage: String = "asdasd"

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityHomeBinding
    private var mapReady = false

    private lateinit var latlongMarcador: String


    companion object {
        val IMAGE_REQUEST_CODE = 100
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle? ): View? {
        // Inflate the layout for this fragment
        db = FirebaseFirestore.getInstance()
        storage = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference

        var rootView = inflater.inflate(R.layout.fragment_formulario_alta_reporte, container, false)

        val mapFragment = childFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment?

        mapFragment?.getMapAsync(this)

        return rootView
    }


    private fun pickImageGallery(view: View) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == IMAGE_REQUEST_CODE) {
            val ivFoto = view_global.findViewById<ImageView>(R.id.ivFoto)
            ivFoto.setImageURI(data?.data)
            //Aca se sube la imagen a firestorage
            filePath = data?.data
            uploadImage()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view_global = view
        val btn = view.findViewById<Button>(R.id.boton_aceptar)
        val btn_elegir_imagen = view.findViewById<Button>(R.id.btn_elegir_imagen)
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
        btn_elegir_imagen.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View) {
                pickImageGallery(view)
            }
        })

        btn.setOnClickListener(View.OnClickListener { view2 ->
            val titulo:String = view.findViewById<EditText>(R.id.editTitulo).text.toString()
            val descripcion = view.findViewById<EditText>(R.id.editDescripcion).text.toString()
            val lugarCaptura = view.findViewById<EditText>(R.id.editLugarCaptura).text.toString()
            val reporte = Reporte(
                titulo = titulo,
                descripcion = descripcion,
                lugarCaptura = latlongMarcador,
                fechaCaptura = fechaCaptura.text.toString(),
                foto = urlImagenEnFireStorage
            )
            //ReporteService.listaReportes = ReporteService.listaReportes + reporte
            ReporteService.agregarReporte(db, reporte)
            activity?.let {
                val intent = Intent (it, HomeActivity::class.java)
                it.startActivity(intent)
            }
        })
    }


    private fun uploadImage(){
        if(filePath != null){
            val ref = storageReference?.child(UUID.randomUUID().toString())
            val uploadTask = ref?.putFile(filePath!!)
            val urlTask = uploadTask?.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                return@Continuation ref.downloadUrl
            })?.addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    val downloadUri = task.result
                    urlImagenEnFireStorage = downloadUri.toString()
                    print("---------------------esta es la urlll")
                    print(urlImagenEnFireStorage)
                } else {
                    // Handle failures
                }
            }?.addOnFailureListener{

            }
        }else{
            Toast.makeText(context, "Please Upload an Image", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        var latitudMarcador: String
        var longitudMarcador: String
        mMap = googleMap
        mMap.setOnMapClickListener { latlng -> // Clears the previously touched position
            latitudMarcador = latlng.latitude.toString()
            longitudMarcador = latlng.longitude.toString()

            latlongMarcador = latitudMarcador.plus("*").plus(longitudMarcador)

            mMap.addMarker(MarkerOptions().position(latlng).title("Este es un marcador")
                .snippet("4 E. 28TH Street From $15 /per night")
                .rotation(-15.0f)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)))
        }
    }

    override fun onMapClick(p0: LatLng?) {
        Toast.makeText(this.context, "sdafdsgfdghfd", Toast.LENGTH_SHORT).show()
    }
}