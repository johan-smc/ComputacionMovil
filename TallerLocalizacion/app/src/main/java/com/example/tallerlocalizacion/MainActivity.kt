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
    }

    private fun goToContactListActivity() {
        val goToContactListIntent = Intent(this, ContactList::class.java)
        startActivity(goToContactListIntent)
    }

    private fun goToLocationActivity() {
        val locationIntent = Intent(this, Location::class.java)
        startActivity(locationIntent)
    }


}
