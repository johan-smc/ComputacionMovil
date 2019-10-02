package com.example.tallerlocalizacion

import android.Manifest.permission.READ_CONTACTS
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.tallerlocalizacion.models.Contact
import kotlinx.android.synthetic.main.activity_contact_list.*


class ContactList : AppCompatActivity() {
    private val permissionsRequestReadContacts = 5
    private var contactAdapter: ContactAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list)
        askPermission()
        loadContacts()
    }

    private fun askPermission() {
        if (ContextCompat.checkSelfPermission(
                this, READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, READ_CONTACTS)) {
                // Show an explanation to the user *asynchronouslyÂ  Â
                Toast.makeText(
                    this,
                    "Se necesita el permiso para poder mostrar los contactos!",
                    Toast.LENGTH_LONG
                ).show()
            }
            // Request the permission.
            ActivityCompat.requestPermissions(
                this,
                arrayOf(READ_CONTACTS),
                permissionsRequestReadContacts
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            permissionsRequestReadContacts -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Acceso a contactos!", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Funcionalidad Limitada!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun loadContacts() {
        val contacts = mutableListOf<Contact>()

        val phones = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC")
        while (phones!!.moveToNext()) {
            val name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            val phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))

            val contactModel = Contact(name, phoneNumber)
            contacts.add(contactModel)
            Log.d("name>>", "$name  $phoneNumber")
        }
        phones.close()
        contactAdapter = ContactAdapter(this, contacts)
        contactsListView.adapter = contactAdapter
    }
}
