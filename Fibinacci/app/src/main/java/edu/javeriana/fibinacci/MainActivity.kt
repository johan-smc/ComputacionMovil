package edu.javeriana.fibinacci

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var fibonacci: IntArray = intArrayOf(0, 1)
    // private val FIBONACCI_IMG_URL = "https://cdn.images.express.co.uk/img/dynamic/109/590x/fibonaci.jpg"
    private lateinit var sequenceTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.sequenceTextView = findViewById(R.id.text_sequence)
        this.populateSequence()
    }

    private fun populateSequence() {
        this.sequenceTextView.text = fibonacci.joinToString()
    }
}
