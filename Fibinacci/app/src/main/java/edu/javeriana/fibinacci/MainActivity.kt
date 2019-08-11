package edu.javeriana.fibinacci

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private lateinit var calculateButton: Button
    private lateinit var sequenceNumberInput: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUi()
    }

    private fun getSequenceNumber(): Int {
        return this.sequenceNumberInput.text.toString().toInt()
    }

    private fun setupUi() {
        this.sequenceNumberInput = findViewById(R.id.edit_text_sequence_number)
        this.calculateButton = findViewById(R.id.button_calculate)
        this.calculateButton.setOnClickListener {
            val intent = Intent(this, SequenceList::class.java)
            intent.putExtra("SequenceNumber", this.getSequenceNumber())
            startActivity(intent)
        }
    }
}
