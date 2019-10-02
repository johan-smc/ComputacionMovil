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

    fun setupButtons() {
        contactsButton.setOnClickListener { goToContactListActivity() }
    }

    fun goToContactListActivity() {
        val goToContactListIntent = Intent(this, ContactList::class.java)
        startActivity(goToContactListIntent)
    }
}
