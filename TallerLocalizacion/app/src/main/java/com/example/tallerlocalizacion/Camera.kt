package com.example.tallerlocalizacion

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_camera.*



class Camera : AppCompatActivity() {
    private val permissionsRequestStorage = 10
    private val galleryRequestCode = 13
    private val imageCaptureCode = 12

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        setupButtons()
    }

    private fun setupButtons() {
        buttonStorage.setOnClickListener { getImageFromStorage() }
        buttonCamera.setOnClickListener { getImageFromCamera() }
    }

    private fun getImageFromCamera() {
        pickFromCamera()
    }

    private fun getImageFromStorage() {
        askPermissionExternalStorage()
        pickFromGallery()
    }

    private fun askPermissionExternalStorage() {
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {
                // Show an explanation to the user *asynchronouslyÂ  Â
                Toast.makeText(
                    this,
                    "Se necesita el permiso para poder obtener imagenes de la galeria!",
                    Toast.LENGTH_LONG
                ).show()
            }
            // Request the permission.
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                permissionsRequestStorage
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            permissionsRequestStorage -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Acceso a Imagenes!", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Funcionalidad Limitada!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun pickFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        /*
         // Other choice
         val intent = Intent(Intent.ACTION_PICK)
         intent.type = "image/\*"
        val mimeTypes = arrayOf("image/jpeg", "image/png")
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
        */




        startActivityForResult(intent, galleryRequestCode)
    }


    private fun pickFromCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, imageCaptureCode)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // Result code is RESULT_OK only if the user selects an Image
        if (resultCode == Activity.RESULT_OK)
            when (requestCode) {
                galleryRequestCode -> {
                    //data.getData returns the content URI for the selected Image
                    val selectedImage = data!!.data
                    imageView.setImageURI(selectedImage)
                }
                imageCaptureCode -> {
                    Log.d("CAPTURE-IMG", "Enter in capture image")
                    val bp = data!!.extras!!.get("data") as Bitmap
                    imageView.setImageBitmap(bp)
                }
            }
    }
}
