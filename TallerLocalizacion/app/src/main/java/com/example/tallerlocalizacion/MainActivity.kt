package com.example.tallerlocalizacion
import android.content.Intent
import kotlinx.android.synthetic.main.activity_main.*

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupButtons()
    }

    private fun setupButtons() {
        contactsButton.setOnClickListener { goToContactListActivity() }
        locationButton.setOnClickListener { goToLocationActivity() }
        cameraButton.setOnClickListener { goToCameraActivity() }
    }

    private fun goToContactListActivity() {
        val goToContactListIntent = Intent(this, ContactList::class.java)
        startActivity(goToContactListIntent)
    }

    private fun goToLocationActivity() {
        val locationIntent = Intent(this, Location::class.java)
        startActivity(locationIntent)
    }

    private fun goToCameraActivity() {
        val cameraIntent = Intent(this, Camera::class.java)
        startActivity(cameraIntent)
    }


}
