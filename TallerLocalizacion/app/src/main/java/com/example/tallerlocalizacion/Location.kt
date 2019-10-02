package com.example.tallerlocalizacion

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.activity_location.*
import android.content.Context.MODE_PRIVATE
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import java.io.FileNotFoundException
import java.io.IOException


class Location : AppCompatActivity() {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    private val kRequestCode = 1000
    private val plazaLocation = Location("")
    private val locations = mutableListOf<Location?>()
    private var lastLocation: Location? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
        plazaLocation.latitude = 4.5981
        plazaLocation.longitude = 74.0760
        setupSaveButton()
        checkPermissions()


    }

    private fun checkPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        )
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                kRequestCode
            )
        else {
            buildLocationRequest()
            buildLocationCallBack()

            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
            if (ActivityCompat.checkSelfPermission(
                    this@Location,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    this@Location,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), kRequestCode)
                return
            }
                fusedLocationProviderClient.requestLocationUpdates(
                    locationRequest,
                    locationCallback,
                    Looper.myLooper()
                )
        }
    }

    private fun setupSaveButton() {
        saveButton.setOnClickListener {
            locations.add(lastLocation)
            val currString = "{N${lastLocation?.latitude}, W${lastLocation?.longitude}}"
            val currTextView = TextView(this)
            currTextView.text = currString
            locationListView.addView(currTextView)
            create(this, "$currString,\n")
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            kRequestCode -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Acceso a localzacion!", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Funcionalidad Limitada!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun buildLocationCallBack() {
        locationCallback = object :LocationCallback() {
            override fun onLocationResult(p0: LocationResult?) {
                val location = p0?.lastLocation
                lastLocation = location
                Log.d("LOCATION", "CAAAALLLLBACCCCKKK")
                latitudTextField.text = location?.latitude.toString()
                longitudTextField.text = location?.longitude.toString()
                altitudTextField.text = location?.altitude.toString()
                val ditanceText = "${plazaLocation.distanceTo(location)}"
                distanciaPlazaTextField.text = ditanceText

            }
        }
    }

    private fun create(context: Context, jsonString: String?): Boolean {
        val FILENAME = "storage.json"
        return try {
            val fos = context.openFileOutput(FILENAME, Context.MODE_APPEND)
            if (jsonString != null) {
                fos.write(jsonString.toByteArray())
            }
            fos.close()
            true
        } catch (fileNotFound: FileNotFoundException) {
            false
        } catch (ioException: IOException) {
            false
        }

    }

    private fun buildLocationRequest() {
        locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 5000
        locationRequest.fastestInterval = 3000
        locationRequest.smallestDisplacement = 10f

    }
}
