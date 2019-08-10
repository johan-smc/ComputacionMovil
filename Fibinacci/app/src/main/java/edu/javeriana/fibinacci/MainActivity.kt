package edu.javeriana.fibinacci

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val fibonacci = mutableListOf(0, 1)
    // private val FIBONACCI_IMG_URL = "https://cdn.images.express.co.uk/img/dynamic/109/590x/fibonaci.jpg"
    private lateinit var sequenceTextView: TextView
    private lateinit var calculateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUi()
    }

    private fun populateSequence() {
        this.sequenceTextView.text = fibonacci.joinToString()
    }

    private fun addNextElementToSequence() {
        val lastIdx = this.fibonacci.lastIndex
        val t1 = this.fibonacci[lastIdx]
        val t2 = this.fibonacci[lastIdx - 1]
        this.fibonacci += t1 + t2
        this.populateSequence()
    }

    private fun setupUi() {
        this.calculateButton = findViewById(R.id.button_calculate)
        this.sequenceTextView = findViewById(R.id.text_sequence)
        this.calculateButton.setOnClickListener { this.addNextElementToSequence() }
        this.populateSequence()
    }
}
